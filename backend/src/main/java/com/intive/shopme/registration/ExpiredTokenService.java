package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbExpiredToken;
import com.intive.shopme.model.db.DbUser;
import com.intive.shopme.model.rest.ExpiredToken;
import com.intive.shopme.model.rest.UserContext;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import static com.intive.shopme.config.AppConfig.EMAIL_CLAIM_NAME;
import static com.intive.shopme.config.AppConfig.SCOPES_CLAIM_NAME;

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
