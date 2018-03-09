package com.intive.shopme.repository;

import com.intive.shopme.model.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {

}