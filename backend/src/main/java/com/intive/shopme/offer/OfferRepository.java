package com.intive.shopme.offer;

import com.intive.shopme.model.db.DbOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface OfferRepository extends JpaRepository<DbOffer, UUID> {

    Page<DbOffer> findAll(Specification<DbOffer> filter, Pageable pageable);
}
