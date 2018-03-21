package com.intive.shopme.service;

import com.intive.shopme.model.Category;
import com.intive.shopme.model.Offer;
import com.intive.shopme.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Transactional
@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> getAll(Specification<Offer> filter) {
        return offerRepository.findAll(filter);
    }

    public Offer get(UUID id) {
        return offerRepository.findOne(id);
    }

    public void add(Offer offer) throws ConstraintViolationException {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Offer>> violations = validator.validate(offer);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        offerRepository.save(offer);
    }

    public void update(UUID id, Offer offer) {
        offerRepository.save(offer);
    }

    public void delete(UUID id) {
        offerRepository.delete(id);
    }
}
