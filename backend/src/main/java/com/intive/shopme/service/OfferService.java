package com.intive.shopme.service;

import com.intive.shopme.model.db.Offer;
import com.intive.shopme.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OfferService {

    @Autowired
    OfferRepository offerRepository;

    public List<Offer> getAll() {
        return offerRepository.findAll();
    }

    public Offer get(UUID id) {
        return offerRepository.findOne(id);
    }

    public void add(Offer offer) {
        offerRepository.save(offer);
    }

    public void update(UUID id, Offer offer) {
        offerRepository.save(offer);
    }

    public void delete(UUID id) {
        offerRepository.delete(id);
    }
}