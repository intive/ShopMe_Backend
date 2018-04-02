package com.intive.shopme.repository;

import com.intive.shopme.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfferRepository extends PagingAndSortingRepository<Offer, UUID> {

    Page<Offer> findAll(Specification<Offer> filter, Pageable pageable);

}
