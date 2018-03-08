package com.intive.shopme.service;

import com.intive.shopme.model.Offer;
import com.intive.shopme.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    @Autowired
    OfferRepository offerRepository;

    public void create(Offer offer) {
        if(offer != null)
        offerRepository.save(offer);
        System.out.println(offer);
    }
}