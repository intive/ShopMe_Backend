package com.intive.shopme.offer;

import com.intive.shopme.offer.model.db.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {

    Page<Offer> findAll(Specification<Offer> filter, Pageable pageable);
}
