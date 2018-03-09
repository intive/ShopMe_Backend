package com.intive.shopme.service;

import com.intive.shopme.model.Offer;
import com.intive.shopme.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    @Autowired
    OfferRepository offerRepository;

    public List<Offer> getAll() {
        return offerRepository.findAll();
    }

    public Offer get(Long id) {
        return offerRepository.findOne(id);
    }

    public void add(Offer offer) {
        offerRepository.save(offer);
    }

    public void update(Long id, Offer offer) {
        offerRepository.save(offer);
    }

    public void delete(Long id) {
        offerRepository.delete(id);
    }

}