package com.intive.shopme.offer;

import com.intive.shopme.model.db.DbOffer;
import com.intive.shopme.model.db.DbUser;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class OfferService {

    private final OfferRepository repository;

    OfferService(OfferRepository repository) {
        this.repository = repository;
    }

    Page<DbOffer> getAll(Specification<DbOffer> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    DbOffer get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataRetrievalFailureException("Offer with id: " + id + " not found"));
    }

    DbOffer createOrUpdate(DbOffer dbOffer) {
        return repository.save(dbOffer);
    }

    void delete(UUID id) {
        repository.deleteById(id);
    }

    public void deleteAllByUser(DbUser user) {
        var offers = repository.findAllByUser(user);
        repository.deleteInBatch(offers);
    }
}
