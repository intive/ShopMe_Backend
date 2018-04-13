package com.intive.shopme.controller;

import com.intive.shopme.controller.filter.OfferSpecificationsBuilder;
import com.intive.shopme.model.Offer;
import com.intive.shopme.service.OfferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import static com.intive.shopme.config.ApiUrl.OFFERS;
import static com.intive.shopme.config.AppConfiguration.ACCEPTABLE_TITLE_SEARCH_CHARS;
import static com.intive.shopme.config.AppConfiguration.DEFAULT_PAGE_SIZE;
import static com.intive.shopme.config.AppConfiguration.DEFAULT_SORT_DIRECTION;
import static com.intive.shopme.config.AppConfiguration.DEFAULT_SORT_FIELD;
import static com.intive.shopme.config.AppConfiguration.FIRST_PAGE;
import static com.intive.shopme.config.AppConfiguration.OFFER_TITLE_MAX_LENGTH;
import static com.intive.shopme.config.AppConfiguration.PAGE_SIZE_MAX;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.CREATED;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.DELETED;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.NOT_FOUND;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.SUCCESS;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.UPDATED;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.VALIDATION_ERROR;

@Validated
@RestController
@RequestMapping(value = OFFERS)
@Api(value = "offer", description = "REST API for offers operations", tags = "Offers")
public class OfferController {

    private final OfferService service;

    public OfferController(OfferService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = CREATED),
            @ApiResponse(code = 422, message = VALIDATION_ERROR)
    })
    @ApiOperation(value = "Saves new offer")
    public Offer add(@RequestBody Offer offer) {
        offer.setId(UUID.randomUUID());
        offer.setDate(new Date());
        return service.createOrUpdate(offer);
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
    public Page<Offer> searchOffers(
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

        final Pageable pageable = PageRequest.of(page - Integer.valueOf(FIRST_PAGE), pageSize,
                Direction.fromString(sortDirection), sortField);

        final OfferSpecificationsBuilder builder = new OfferSpecificationsBuilder();
        if (StringUtils.isNotEmpty(title) && (title.length() > 1) && !StringUtils.isNumeric(title)) {
            var titleKeywords = StringUtils.left(title, OFFER_TITLE_MAX_LENGTH)
                    .replaceAll("[^" + ACCEPTABLE_TITLE_SEARCH_CHARS + "]", "")
                    .replaceAll("  ", " ")
                    .toLowerCase().split(" ");
            Arrays.stream(titleKeywords).forEach(titleKeyword -> builder.with("title", ":", titleKeyword));
        }
        final Specification<Offer> filter = builder.build();
        
        if (dateMin != 0) builder.with("date", "≥", dateMin);
        if (dateMax != 0) builder.with("date", "≤", dateMax);
        if (priceMin != 0) builder.with("basePrice", "≥", priceMin);
        if (priceMax != 0) builder.with("basePrice", "≤", priceMax);

        return service.getAll(pageable, filter);
    }

    @GetMapping(value = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 404, message = NOT_FOUND)
    })
    @ApiOperation(value = "Returns offer by id")
    public Offer get(@PathVariable UUID id) {
        return service.get(id);
    }

    @PutMapping(value = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = UPDATED),
            @ApiResponse(code = 404, message = NOT_FOUND)
    })
    @ApiOperation(value = "Updates offer by id")
    public Offer update(Offer offer) {
        return service.createOrUpdate(offer);
    }

    @DeleteMapping(value = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = DELETED),
            @ApiResponse(code = 404, message = NOT_FOUND)
    })
    @ApiOperation(value = "Removes offer by id")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
