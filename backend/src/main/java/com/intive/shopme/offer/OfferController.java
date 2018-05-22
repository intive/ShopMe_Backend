package com.intive.shopme.offer;

import com.intive.shopme.category.CategoryValidator;
import com.intive.shopme.common.ConvertibleController;
import com.intive.shopme.model.db.DbCategory;
import com.intive.shopme.model.db.DbOffer;
import com.intive.shopme.model.db.DbOwner;
import com.intive.shopme.model.db.DbVoivodeship;
import com.intive.shopme.model.rest.Category;
import com.intive.shopme.model.rest.Offer;
import com.intive.shopme.model.rest.Owner;
import com.intive.shopme.model.rest.UserContext;
import com.intive.shopme.model.rest.Voivodeship;
import com.intive.shopme.registration.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.intive.shopme.config.ApiUrl.OFFERS;
import static com.intive.shopme.config.AppConfig.CONSTRAINTS_JSON_KEY;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.BAD_REQUEST;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.CREATED;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.DELETED;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.NOT_FOUND;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.SUCCESS;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.UPDATED;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.VALIDATION_ERROR;

@RestController
@RequestMapping(value = OFFERS)
@Api(value = "offer", description = "REST API for offers operations", tags = "Offers")
public class OfferController extends ConvertibleController<DbOffer, Offer> {

    private final OfferService service;
    private final UserService userService;
    private final Validator categoryValidator;

    OfferController(OfferService service, UserService userService, CategoryValidator validator) {
        super(DbOffer.class, Offer.class);
        this.service = service;
        this.userService = userService;
        this.categoryValidator = validator;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = CREATED),
            @ApiResponse(code = 400, message = BAD_REQUEST),
            @ApiResponse(code = 422, message = VALIDATION_ERROR)
    })
    @ApiOperation(value = "Saves new offer", response = Offer.class)
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> add(@Valid @RequestBody Offer offer, Errors errors,
                                 @ApiIgnore @AuthenticationPrincipal UserContext userContext) {
        final var authenticatedUser = userService.get(userContext.getUserId());
        final var additionalInfo = offer.getOwner().getAdditionalInfo();
        offer.setOwner(new Owner(authenticatedUser, additionalInfo));

        categoryValidator.validate(offer, errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(Map.of(CONSTRAINTS_JSON_KEY, createErrorString(errors)), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        final var dbOffer = convertToDbModel(offer);
        dbOffer.setId(UUID.randomUUID());
        dbOffer.setDate(new Date());
        return ResponseEntity.ok(convertToView(service.createOrUpdate(dbOffer)));
    }

    private static String createErrorString(Errors errors) {
        return errors.getAllErrors().stream()
                .map(ObjectError::toString)
                .collect(Collectors.joining(","));
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 400, message = BAD_REQUEST)
    })
    @ApiOperation(value = "Returns all existing offers (with optional paging, filter criteria and sort strategy)")
    Page<Offer> search(@Valid OfferSearchParams offerSearchParams) {
        final var offers = service.getAll(offerSearchParams);
        return new PageImpl<>(convertToView(offers.getContent()), offerSearchParams.pageable(), offers.getTotalElements());
    }

    @GetMapping(value = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 404, message = NOT_FOUND)
    })
    @ApiOperation(value = "Returns offer by id")
    Offer get(@PathVariable UUID id) {
        return convertToView(service.get(id));
    }

    @PutMapping(value = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = UPDATED),
            @ApiResponse(code = 404, message = NOT_FOUND)
    })
    @ApiOperation(value = "Updates offer by id")
    @PreAuthorize("hasAnyAuthority('USER')")
    Offer update(Offer offer) {
        final var dbOffer = convertToDbModel(offer);
        return convertToView(service.createOrUpdate(dbOffer));
    }

    @DeleteMapping(value = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = DELETED),
            @ApiResponse(code = 404, message = NOT_FOUND)
    })
    @ApiOperation(value = "Removes offer by id")
    @PreAuthorize("hasAnyAuthority('USER')")
    void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @Override
    protected Offer convertToView(DbOffer dbOffer) {
        final var result = new Offer();
        result.setId(dbOffer.getId());
        result.setDate(dbOffer.getDate());
        result.setTitle(dbOffer.getTitle());
        result.setBasePrice(dbOffer.getBasePrice());
        result.setBaseDescription(dbOffer.getBaseDescription());
        result.setExtendedPrice(dbOffer.getExtendedPrice());
        result.setExtendedDescription(dbOffer.getExtendedDescription());
        result.setExtraPrice(dbOffer.getExtraPrice());
        result.setExtraDescription(dbOffer.getExtraDescription());
        if (dbOffer.getOwner() != null) {
            result.setOwner(genericConvert(dbOffer.getOwner(), Owner.class));
            if (dbOffer.getOwner().getVoivodeship() != null) {
                result.getOwner().setVoivodeship(genericConvert(dbOffer.getOwner().getVoivodeship(), Voivodeship.class));
            }
        }
        if (dbOffer.getCategory() != null) {
            result.setCategory(genericConvert(dbOffer.getCategory(), Category.class));
        }
        return result;
    }

    @Override
    protected DbOffer convertToDbModel(Offer offerView) {
        final var result = new DbOffer();
        result.setId(offerView.getId());
        result.setDate(offerView.getDate());
        result.setTitle(offerView.getTitle());
        result.setBasePrice(offerView.getBasePrice());
        result.setBaseDescription(offerView.getBaseDescription());
        result.setExtendedPrice(offerView.getExtendedPrice());
        result.setExtendedDescription(offerView.getExtendedDescription());
        result.setExtraPrice(offerView.getExtraPrice());
        result.setExtraDescription(offerView.getExtraDescription());
        if (offerView.getOwner() != null) {
            result.setOwner(genericConvert(offerView.getOwner(), DbOwner.class));
            if (offerView.getOwner().getVoivodeship() != null) {
                result.getOwner().setVoivodeship(genericConvert(offerView.getOwner().getVoivodeship(), DbVoivodeship.class));
            }
        }
        if (offerView.getCategory() != null) {
            result.setCategory(genericConvert(offerView.getCategory(), DbCategory.class));
        }
        return result;
    }
}
