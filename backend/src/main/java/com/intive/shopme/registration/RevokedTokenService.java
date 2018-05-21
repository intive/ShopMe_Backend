package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbRevokedToken;
import com.intive.shopme.model.rest.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RevokedTokenService {

    private final RevokedTokenRepository repository;

    @Value("${jwt.minimum-revoked-tokens-remove-interval}")
    private long revokedTokenInterval;
    long lastTokenRemoval = 0L;

    @Autowired
    public RevokedTokenService(RevokedTokenRepository repository) {
        this.repository = repository;
    }

    void logout(DbRevokedToken dbRevokedToken) {
        removeExpiredTokens();
        repository.save(dbRevokedToken);
    }

    public boolean isRevoked(UserContext userContext) {
        UUID userId = userContext.getUserId();
        Date expirationDate = userContext.getExpirationDate();
        return repository.findOneByUserIdAndExpirationDate(userId, expirationDate) != null;
    }

    void removeExpiredTokens() {
        Date currentTime = new Date();
        if (lastTokenRemoval + revokedTokenInterval < currentTime.toInstant().toEpochMilli()) {
            List<DbRevokedToken> revokedTokenList = repository.findAll();
            for (DbRevokedToken dbRevokedToken : revokedTokenList) {
                if (dbRevokedToken.getExpirationDate().before(currentTime)) {
                    repository.delete(dbRevokedToken);
                }
            }
            lastTokenRemoval = System.currentTimeMillis();
        }
    }
}
