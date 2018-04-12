package com.intive.shopme.service;

import com.intive.shopme.model.Offer;
import com.intive.shopme.repository.OfferRepository;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class OfferService extends Validated<Offer> {

    private final OfferRepository repository;

    public OfferService(OfferRepository repository) {
        this.repository = repository;
    }

    public Page<Offer> getAll(Pageable pageable, Specification<Offer> filter) {
        return repository.findAll(filter, pageable);
    }

    public Offer get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataRetrievalFailureException("Offer with id: " + id + " not found"));
    }

    public Offer createOrUpdate(Offer offer) {
        validate(offer);
        return repository.save(offer);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
