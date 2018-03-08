package com.intive.shopme.controller;

import com.intive.shopme.model.Offer;
import com.intive.shopme.service.OfferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "offer", description = "Rest API for offers", tags = "Offer API")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @ApiOperation(value = "Add offer to repository")
    @RequestMapping(method = RequestMethod.POST, value = "/offers")
    public void create(@RequestBody Offer offer) {
        offerService.create(offer);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/offe")
    public String get() {

        return "cos";
    }
}