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
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.intive.shopme.config.ApiUrl.OFFERS;
import static com.intive.shopme.config.AppConfig.ACCEPTABLE_TITLE_SEARCH_CHARS;
import static com.intive.shopme.config.AppConfig.DEFAULT_PAGE;
import static com.intive.shopme.config.AppConfig.DEFAULT_PAGE_SIZE;
import static com.intive.shopme.config.AppConfig.DEFAULT_SORT_DIRECTION;
import static com.intive.shopme.config.AppConfig.DEFAULT_SORT_FIELD;
import static com.intive.shopme.config.AppConfig.FIRST_PAGE;
import static com.intive.shopme.config.AppConfig.OFFER_TITLE_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.PAGE_SIZE_MAX;

@Validated
@RestController
@RequestMapping(value = OFFERS)
@Api(value = "offer", description = "REST API for offers", tags = "Offers")
public class OfferController {

    private final OfferService service;

    @Autowired
    public OfferController(OfferService service) {
        this.service = service;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New offer successfully created"),
            @ApiResponse(code = 422, message = "New offer data validation error")
    })
    @ApiOperation(value = "Saves offer")
    @PostMapping
    public void add(@RequestBody Offer offer) {
        offer.setId(UUID.randomUUID());
        offer.setDate(new Date());
        service.add(offer);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "requested page number (optional, counting from " + FIRST_PAGE +
                    ", default = " + DEFAULT_PAGE + ")", defaultValue = DEFAULT_PAGE,
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
            @ApiResponse(code = 200, message = "Successfully retrieved offer(s)"),
    })
    @ApiOperation(value = "Returns all existing offers (with optional paging, filter criteria and sort strategy)")
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public Page<Offer> searchOffers(@RequestParam(name = "page", required = false) Optional<Integer> page,
                                    @RequestParam(name = "pageSize", required = false) Optional<Integer> pageSize,
                                    @RequestParam(name = "sort", required = false) Optional<String> sort,
                                    @RequestParam(name = "order", required = false) Optional<String> order,
                                    @RequestParam(name = "title", required = false) Optional<String> title,
                                    @RequestParam(name = "priceMin", required = false) Optional<Float> priceMin,
                                    @RequestParam(name = "priceMax", required = false) Optional<Float> priceMax,
                                    @RequestParam(name = "dateMin", required = false) Optional<Long> dateMin,
                                    @RequestParam(name = "dateMax", required = false) Optional<Long> dateMax) {

        final int pageNumber;
        if (page.isPresent()) {
            pageNumber = page.get() < FIRST_PAGE ? Integer.valueOf(DEFAULT_PAGE) : page.get();
        } else {
            pageNumber = Integer.valueOf(DEFAULT_PAGE);
        }

        final int pageSizeNumber;
        if (pageSize.isPresent()) {
            pageSizeNumber = pageSize.get() < PAGE_SIZE_MAX ? pageSize.get() : PAGE_SIZE_MAX;
        } else {
            pageSizeNumber = Integer.valueOf(DEFAULT_PAGE_SIZE);
        }

        final String sortField = sort.orElse(DEFAULT_SORT_FIELD);
        final String sortOrder = order.orElse(DEFAULT_SORT_DIRECTION);

        final Pageable pageable = PageRequest.of(pageNumber - FIRST_PAGE, pageSizeNumber,
                Direction.fromString(sortOrder), sortField);

        final OfferSpecificationsBuilder builder = new OfferSpecificationsBuilder();
        if (title.isPresent() && (title.get().length() > 1) && !title.get().matches("^[0-9]{2,}$")) {
            String[] titleKeywords = title.get()
                    .substring(0, title.get().length() > OFFER_TITLE_MAX_LENGTH ?
                            OFFER_TITLE_MAX_LENGTH : title.get().length())
                    .replaceAll("[^" + ACCEPTABLE_TITLE_SEARCH_CHARS + "]", "")
                    .toLowerCase().split(" ");
            for (String titleKeyword : titleKeywords) {
                builder.with("title", ":", titleKeyword);
            }
        }
        final Specification<Offer> filter = builder.build();

        dateMin.ifPresent(aLong -> builder.with("date", "≥", aLong));
        dateMax.ifPresent(aLong -> builder.with("date", "≤", aLong));
        priceMin.ifPresent(aFloat -> builder.with("basePrice", "≥", aFloat));
        priceMax.ifPresent(aFloat -> builder.with("basePrice", "≤", aFloat));

        return service.getAll(pageable, filter);
    }

    @ApiOperation(value = "Returns offer by id")
    @GetMapping(value = "{id}")
    public Offer get(@PathVariable UUID id) {
        return service.get(id);
    }

    @ApiOperation(value = "Updates offer by id")
    @PutMapping(value = "{id}")
    public void update(Offer offer) {
        service.update(offer);
    }

    @ApiOperation(value = "Removes offer by id")
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

}
