package com.intive.shopme.controller;

import com.intive.shopme.model.Offer;
import com.intive.shopme.service.OfferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Api(value = "offer", description = "Rest API for offers", tags = "Offer API")
public class OfferController {


    @Autowired
    private OfferService offerService;

    @ApiOperation(value = "Add offer to repository")
    @PostMapping(value = "/offers")
    public void add(@RequestBody Offer offer) {
        offerService.add(offer);
    }

    @ApiOperation(value = "Display all existing offers")
    @GetMapping(value = "/offers")
    public List<Offer> getAllOffers() {
        return offerService.getAll();
    }

    @ApiOperation(value = "Display offer by id")
    @GetMapping(value = "/offers/{id}")
    public Offer get(@PathVariable UUID id) {
        return offerService.get(id);
    }

    @ApiOperation(value = "Update offer by id ")
    @PutMapping(value = "/offers/{id}")
    public void update(@PathVariable UUID id, Offer offer) {
        offerService.update(id, offer);
    }

    @ApiOperation(value = "Delete offer by id")
    @DeleteMapping(value = "/offers/{id}")
    public void delete(@PathVariable UUID id) {
        offerService.delete(id);
    }
}