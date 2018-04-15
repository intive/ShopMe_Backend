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
import com.intive.shopme.model.rest.Voivodeship;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.intive.shopme.config.ApiUrl.OFFERS;
import static com.intive.shopme.config.AppConfig.ACCEPTABLE_TITLE_SEARCH_CHARS;
import static com.intive.shopme.config.AppConfig.DEFAULT_PAGE_SIZE;
import static com.intive.shopme.config.AppConfig.DEFAULT_SORT_DIRECTION;
import static com.intive.shopme.config.AppConfig.DEFAULT_SORT_FIELD;
import static com.intive.shopme.config.AppConfig.FIRST_PAGE;
import static com.intive.shopme.config.AppConfig.OFFER_TITLE_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.PAGE_SIZE_MAX;
import static com.intive.shopme.config.ErrorHandlingConfig.CONSTRAINTS_JSON_KEY;
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
    private final Validator categoryValidator;

    OfferController(OfferService service, CategoryValidator validator) {
        super(DbOffer.class, Offer.class);
        this.service = service;
        this.categoryValidator = validator;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = CREATED),
            @ApiResponse(code = 422, message = VALIDATION_ERROR)
    })
    @ApiOperation(value = "Saves new offer", response = Offer.class)
    public ResponseEntity<?> add(@Valid @RequestBody Offer offer, Errors errors) {
        categoryValidator.validate(offer, errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(Map.of(CONSTRAINTS_JSON_KEY, createErrorString(errors)), HttpStatus.BAD_REQUEST);
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "requested page number (optional, counting from " + FIRST_PAGE +
                    ", default = " + FIRST_PAGE + ")", defaultValue = FIRST_PAGE,
                    dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "number of offers per page (optional, default = " +
                    DEFAULT_PAGE_SIZE + ", max " + PAGE_SIZE_MAX + ")",
                    allowableValues = "range[1, " + PAGE_SIZE_MAX + "]", defaultValue = DEFAULT_PAGE_SIZE,
                    dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "the properties to sort by (optional, date | basePrice | title, default = " +
                    DEFAULT_SORT_FIELD + ")",
                    allowableValues = "date, basePrice, title", defaultValue = DEFAULT_SORT_FIELD,
                    dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "order", value = "sorting order (optional, ASC | DESC, default = " +
                    DEFAULT_SORT_DIRECTION + ")",
                    allowableValues = "ASC, DESC", defaultValue = DEFAULT_SORT_DIRECTION,
                    dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "filter query for offers titles (optional, at least two characters)",
                    dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "priceMin", value = "minimum price (optional)",
                    dataType = "Float", paramType = "query"),
            @ApiImplicitParam(name = "priceMax", value = "maximum price (optional)",
                    dataType = "Float", paramType = "query"),
            @ApiImplicitParam(name = "dateMin", value = "offer not older than (optional)",
                    dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "dateMax", value = "offer not newer than (optional)",
                    dataType = "Long", paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
    })
    @ApiOperation(value = "Returns all existing offers (with optional paging, filter criteria and sort strategy)")
    Page<Offer> searchOffers(
            @RequestParam(name = "page", required = false, defaultValue = FIRST_PAGE) int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(name = "sort", required = false, defaultValue = DEFAULT_SORT_FIELD) String sortField,
            @RequestParam(name = "order", required = false, defaultValue = DEFAULT_SORT_DIRECTION) String sortDirection,
            @RequestParam(name = "title", required = false, defaultValue = "") String title,
            @RequestParam(name = "priceMin", required = false, defaultValue = "0") float priceMin,
            @RequestParam(name = "priceMax", required = false, defaultValue = "0") float priceMax,
            @RequestParam(name = "dateMin", required = false, defaultValue = "0") long dateMin,
            @RequestParam(name = "dateMax", required = false, defaultValue = "0") long dateMax) {

        page = Math.max(page, Integer.valueOf(FIRST_PAGE));
        pageSize = Math.min(pageSize, PAGE_SIZE_MAX);

        final var pageable = PageRequest.of(page - Integer.valueOf(FIRST_PAGE), pageSize,
                Direction.fromString(sortDirection), sortField);

        final var builder = new OfferSpecificationsBuilder();
        if (StringUtils.isNotEmpty(title) && (title.length() > 1) && !StringUtils.isNumeric(title)) {
            var titleKeywords = StringUtils.left(title, OFFER_TITLE_MAX_LENGTH)
                    .replaceAll("[^" + ACCEPTABLE_TITLE_SEARCH_CHARS + "]", "")
                    .replaceAll("  ", " ")
                    .toLowerCase().split(" ");
            Arrays.stream(titleKeywords).forEach(titleKeyword -> builder.with("title", ":", titleKeyword));
        }
        final var filter = builder.build();

        if (dateMin != 0) builder.with("date", "≥", dateMin);
        if (dateMax != 0) builder.with("date", "≤", dateMax);
        if (priceMin != 0) builder.with("basePrice", "≥", priceMin);
        if (priceMax != 0) builder.with("basePrice", "≤", priceMax);

        final var offers = service.getAll(pageable, filter);
        return new PageImpl<>(convertToView(offers.getContent()), pageable, offers.getTotalElements());
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
