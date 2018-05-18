package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbUser;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserService {

    private final UserRepository repository;

    UserService(UserRepository repository) {
        this.repository = repository;
    }

    DbUser createOrUpdate(DbUser dbUser) {
        if (!dbUser.getInvoiceRequest()) {
            dbUser.setInvoice(null);
        }
        return repository.save(dbUser);
    }

    public DbUser get(UUID id) {
        repository.getOne(id);
        return repository.findById(id)
                .orElseThrow(() -> new DataRetrievalFailureException("User with id: " + id + " not found"));
    }

    boolean findIfUserExist(String email) {
        return !repository.findUserByEmail(email).isEmpty();
    }

    public DbUser findOneByEmail(String email) {
        return repository.findOneByEmail(email);
    }
}
