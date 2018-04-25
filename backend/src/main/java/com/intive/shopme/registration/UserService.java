package com.intive.shopme.registration;

import com.intive.shopme.common.Validated;
import com.intive.shopme.model.db.DbUser;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
class UserService extends Validated<DbUser> {

    private final UserRepository repository;

    UserService(UserRepository repository) {
        this.repository = repository;
    }

    DbUser createOrUpdate(DbUser dbUser) {
        if (dbUser.getInvoiceRequest() != null && !dbUser.getInvoiceRequest()) {
            dbUser.setInvoice(null);
        }
        validate(dbUser);
        return repository.save(dbUser);
    }

    DbUser get(UUID id) {
        repository.getOne(id);
        return repository.findById(id)
                .orElseThrow(() -> new DataRetrievalFailureException("User with id: " + id + " not found"));
    }

    boolean findIfUserExist(String email) {
        return !repository.findUserByEmail(email).isEmpty();
    }
}
