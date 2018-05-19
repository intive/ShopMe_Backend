package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbExpiredToken;
import com.intive.shopme.model.rest.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Transactional
public class ExpiredTokenService {

    private final ExpiredTokenRepository repository;

    @Autowired
    public ExpiredTokenService(ExpiredTokenRepository repository) {
        this.repository = repository;
    }

    boolean findOneByUserIdAndExpirationDate(UUID userId, Long expirationDate) {
        if (repository.findOneByUserIdAndExpirationDate(userId, expirationDate) != null) {
            return true;
        } else
            return false;

    }

    DbExpiredToken logout(DbExpiredToken dbExpiredToken) {
        return repository.save(dbExpiredToken);
    }

    public boolean isTokenExpired(UserContext userContext) {

        UUID userId = userContext.getUserId();
        Long expirationDate = userContext.getExpirationDate();
        if (repository.findOneByUserIdAndExpirationDate(userId, expirationDate) != null) {

            return true;
        } else {
            return false;
        }

    }

}
