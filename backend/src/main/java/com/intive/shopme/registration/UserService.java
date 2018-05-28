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

    void delete(UUID id) {
        repository.deleteById(id);
    }

    boolean existsByEmail(String email) {
        return repository.existsByEmail(email.toLowerCase());
    }

    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    public DbUser findOneByEmail(String email) {
        return repository.findOneByEmail(email);
    }
}
