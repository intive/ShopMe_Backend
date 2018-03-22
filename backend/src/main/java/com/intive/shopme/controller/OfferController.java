package com.intive.shopme.controller;

import com.intive.shopme.model.Offer;
import com.intive.shopme.service.OfferService;
import com.intive.shopme.controller.filter.OfferSpecificationsBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.intive.shopme.config.AppConfig.OFFERS_PATH;
import static com.intive.shopme.config.AppConfig.OFFER_TITLE_MAX_LENGTH;

@Validated
@RestController
@RequestMapping(value = OFFERS_PATH)
@Api(value = "offer", description = "REST API for offers", tags = "Offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New offer successfully created"),
            @ApiResponse(code = 422, message = "New offer data validation error")
    })
    @ApiOperation(value = "Saves offer")
    @PostMapping
    public void add(@RequestBody Offer offer) throws ConstraintViolationException {
        offer.setId(UUID.randomUUID());
        offerService.add(offer);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "filter query for offers titles (optional)",
                    required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "priceMin", value = "minimum price (optional)",
                    required = false, dataType = "Float", paramType = "query"),
            @ApiImplicitParam(name = "priceMax", value = "maximum price (optional)",
                    required = false, dataType = "Float", paramType = "query"),
            @ApiImplicitParam(name = "dateMin", value = "offer not older than (optional)",
                    required = false, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "dateMax", value = "offer not newer than (optional)",
                    required = false, dataType = "Long", paramType = "query")
    })
    @ApiOperation(value = "Returns all existing offers (with optional filter criteria)")
    @GetMapping
    public List<Offer> searchOffers(@RequestParam(required = false) Optional<String> title,
                                    @RequestParam(required = false) Optional<Float> priceMin,
                                    @RequestParam(required = false) Optional<Float> priceMax,
                                    @RequestParam(required = false) Optional<Long> dateMin,
                                    @RequestParam(required = false) Optional<Long> dateMax) {
        OfferSpecificationsBuilder builder = new OfferSpecificationsBuilder();
        if (title.isPresent()) {
            String[] titleKeywords = title.get()
                    .substring(0, title.get().length() > OFFER_TITLE_MAX_LENGTH ?
                            OFFER_TITLE_MAX_LENGTH : title.get().length())
                    .replaceAll("[^ąĄćĆęĘłŁńŃóÓśŚżŻźŹa-zA-z0-9 ]", "")
                    .toLowerCase().split(" ");
            for (String titleKeyword : titleKeywords) {
                boolean compliant = !titleKeyword.matches("^.$") &&
                        !titleKeyword.matches("^[0-9]{2,}$");
                if (compliant) {
                    builder.with("title", ":", titleKeyword);
                }
            }
        }
        dateMin.ifPresent(aLong -> builder.with("date", ">", aLong));
        dateMax.ifPresent(aLong -> builder.with("date", "<", aLong));
        priceMin.ifPresent(aFloat -> builder.with("basePrice", ">", aFloat));
        priceMax.ifPresent(aFloat -> builder.with("basePrice", "<", aFloat));

        Specification<Offer> filter = builder.build();
        return offerService.getAll(filter);
    }

    @ApiOperation(value = "Returns offer by id")
    @GetMapping(value = "/{id}")
    public Offer get(@PathVariable UUID id) {
        return offerService.get(id);
    }

    @ApiOperation(value = "Updates offer by id")
    @PutMapping(value = "/{id}")
    public void update(@PathVariable UUID id, Offer offer) {
        offerService.update(id, offer);
    }

    @ApiOperation(value = "Removes offer by id")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable UUID id) {
        offerService.delete(id);
    }

}
