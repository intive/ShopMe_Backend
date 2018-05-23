package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbRevokedToken;
import com.intive.shopme.model.rest.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class RevokedTokenService {

    private final RevokedTokenRepository repository;

    @Value("${jwt.minimum-revoked-tokens-remove-interval}")
    private static long revokedTokenInterval;
    private static long lastTokenRemoval = 0L;

    @Autowired
    public RevokedTokenService(RevokedTokenRepository repository) {
        this.repository = repository;
    }

    void logout(DbRevokedToken dbRevokedToken) {
        removeExpiredTokens();
        repository.save(dbRevokedToken);
    }

    public boolean isRevoked(UserContext userContext) {
        final var userId = userContext.getUserId();
        final var expirationDate = userContext.getExpirationDate();
        return repository.findOneByUserIdAndExpirationDate(userId, expirationDate) != null;
    }

    private void removeExpiredTokens() {
        final var currentTime = new Date().toInstant().toEpochMilli();

        if (lastTokenRemoval + revokedTokenInterval < currentTime) {

            try {
                lastTokenRemoval = System.currentTimeMillis();
                repository.removeExpiredTokens();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
