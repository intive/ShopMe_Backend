package com.intive.shopme.offer;

import com.intive.shopme.category.CategoryValidator;
import com.intive.shopme.common.ConvertibleController;
import com.intive.shopme.model.db.DbCategory;
import com.intive.shopme.model.db.DbOffer;
import com.intive.shopme.model.db.DbVoivodeship;
import com.intive.shopme.model.rest.OfferView;
import com.intive.shopme.model.rest.OfferWrite;
import com.intive.shopme.model.rest.UserContext;
import com.intive.shopme.registration.UserService;
import com.intive.shopme.voivodeship.VoivodeshipValidator;
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
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.FORBIDDEN;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.NOT_FOUND;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.SUCCESS;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.UNAUTHORIZED;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.UPDATED;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.VALIDATION_ERROR;

@RestController
@RequestMapping(value = OFFERS)
@Api(value = "offer", description = "REST API for offers operations", tags = "Offers")
public class OfferController extends ConvertibleController<DbOffer, OfferView, OfferWrite> {

    private final OfferService service;
    private final UserService userService;
    private final Validator categoryValidator;
    private final Validator voivodeshipValidator;

    OfferController(OfferService service, UserService userService,
                    CategoryValidator categoryValidator, VoivodeshipValidator voivodeshipValidator) {
        super(DbOffer.class, OfferView.class, OfferWrite.class);
        this.service = service;
        this.userService = userService;
        this.categoryValidator = categoryValidator;
        this.voivodeshipValidator = voivodeshipValidator;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = CREATED),
            @ApiResponse(code = 400, message = BAD_REQUEST),
            @ApiResponse(code = 401, message = UNAUTHORIZED),
            @ApiResponse(code = 422, message = VALIDATION_ERROR)
    })
    @ApiOperation(value = "Saves new offer", response = OfferView.class)
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> add(@Valid @RequestBody OfferWrite offer, Errors errors,
                                 @ApiIgnore @AuthenticationPrincipal UserContext userContext) {
        categoryValidator.validate(offer.getCategory(), errors);
        voivodeshipValidator.validate(offer.getVoivodeship(), errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(Map.of(CONSTRAINTS_JSON_KEY, createErrorString(errors)),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        final var dbOffer = convertToDbModel(offer);
        final var authenticatedUser = userService.get(userContext.getUserId());
        dbOffer.setId(UUID.randomUUID());
        dbOffer.setDate(new Date());
        dbOffer.setUser(authenticatedUser);
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
            @ApiResponse(code = 400, message = BAD_REQUEST),
            @ApiResponse(code = 422, message = VALIDATION_ERROR)
    })
    @ApiOperation(value = "Returns all existing offers (with optional paging, filter criteria and sort strategy)")
    Page<OfferView> search(@Valid OfferSearchParams offerSearchParams) {
        final var filter = offerSearchParams.filter();

        final var userId = offerSearchParams.getUserId();
        if (userId != null) {
            if (userService.existsById(userId)) {
                final var user = userService.get(offerSearchParams.getUserId());
                filter.with("user", ":", user);
            } else {
                filter.empty();
            }
        }

        final var offers = service.getAll(filter.build(), offerSearchParams.pageable());
        return new PageImpl<>(convertToView(offers.getContent()), offerSearchParams.pageable(), offers.getTotalElements());
    }

    @GetMapping(value = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 404, message = NOT_FOUND)
    })
    @ApiOperation(value = "Returns offer by id")
    OfferView get(@PathVariable UUID id) {
        return convertToView(service.get(id));
    }

    @PutMapping(value = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = UPDATED),
            @ApiResponse(code = 401, message = UNAUTHORIZED),
            @ApiResponse(code = 403, message = FORBIDDEN),
            @ApiResponse(code = 404, message = NOT_FOUND),
            @ApiResponse(code = 422, message = VALIDATION_ERROR)
    })
    @ApiOperation(value = "Updates offer by id", response = OfferView.class)
    @PreAuthorize("hasAnyAuthority('USER')")
    ResponseEntity<?> update(@PathVariable UUID id, @Valid @RequestBody OfferWrite offer, Errors errors,
                             @ApiIgnore @AuthenticationPrincipal UserContext userContext) {
        final var offerAuthor = service.get(id).getUser();
        final var authenticatedUser = userService.get(userContext.getUserId());
        if (!authenticatedUser.equals(offerAuthor)) {
            return new ResponseEntity<>(Map.of("message", FORBIDDEN), HttpStatus.FORBIDDEN);
        }

        categoryValidator.validate(offer.getCategory(), errors);
        voivodeshipValidator.validate(offer.getVoivodeship(), errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(Map.of(CONSTRAINTS_JSON_KEY, createErrorString(errors)), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        final var dbOffer = convertToDbModel(offer);
        dbOffer.setId(id);
        dbOffer.setDate(new Date());
        dbOffer.setUser(authenticatedUser);
        return ResponseEntity.ok(convertToView(service.createOrUpdate(dbOffer)));
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = DELETED),
            @ApiResponse(code = 401, message = UNAUTHORIZED),
            @ApiResponse(code = 403, message = FORBIDDEN),
            @ApiResponse(code = 404, message = NOT_FOUND)
    })
    @ApiOperation(value = "Removes offer by id", response = void.class)
    @PreAuthorize("hasAnyAuthority('USER')")
    ResponseEntity<?> delete(@PathVariable UUID id, @ApiIgnore @AuthenticationPrincipal UserContext userContext) {
        final var offerAuthorId = service.get(id).getUser();
        final var authenticatedUserId = userService.get(userContext.getUserId());
        if (!authenticatedUserId.equals(offerAuthorId)) {
            return new ResponseEntity<>(Map.of("message", FORBIDDEN), HttpStatus.FORBIDDEN);
        }

        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    protected OfferView convertToView(DbOffer dbOffer) {
        final var result = new OfferView();

        result.setId(dbOffer.getId());
        result.setDate(dbOffer.getDate());
        result.setTitle(dbOffer.getTitle());
        result.setCategory(dbOffer.getCategory().getName());
        result.setBasePrice(dbOffer.getBasePrice());
        result.setBaseDescription(dbOffer.getBaseDescription());
        if (dbOffer.isExtendedComplete()) {
            result.setExtendedPrice(dbOffer.getExtendedPrice());
            result.setExtendedDescription(dbOffer.getExtendedDescription());
        }

        if (dbOffer.isExtraComplete()) {
            result.setExtraPrice(dbOffer.getExtraPrice());
            result.setExtraDescription(dbOffer.getExtraDescription());
        }

        final var user = dbOffer.getUser();

        result.setUser(user.getId());
        result.setName(user.getName());
        result.setSurname(user.getSurname());
        result.setEmail(user.getEmail());
        result.setPhoneNumber(user.getPhoneNumber());
        result.setAdditionalInfo(user.getAdditionalInfo());

        result.setVoivodeship(dbOffer.getVoivodeship().getName());
        result.setCity(dbOffer.getCity());

        return result;
    }

    @Override
    protected DbOffer convertToDbModel(OfferWrite offer) {
        final var result = new DbOffer();

        result.setTitle(offer.getTitle());
        result.setCategory(new DbCategory(offer.getCategory()));
        result.setBasePrice(offer.getBasePrice());
        result.setBaseDescription(offer.getBaseDescription());

        if (offer.isExtendedComplete()) {
            result.setExtendedPrice(offer.getExtendedPrice());
            result.setExtendedDescription(offer.getExtendedDescription());
        }

        if (offer.isExtraComplete()) {
            result.setExtraPrice(offer.getExtraPrice());
            result.setExtraDescription(offer.getExtraDescription());
        }

        result.setVoivodeship(new DbVoivodeship(offer.getVoivodeship()));
        result.setCity(offer.getCity());

        return result;
    }
}
