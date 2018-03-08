package com.intive.shopme.repository;

import com.intive.shopme.model.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer, String> {

}