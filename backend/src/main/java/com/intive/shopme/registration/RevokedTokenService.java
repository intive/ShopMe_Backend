package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbRevokedToken;
import com.intive.shopme.model.rest.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RevokedTokenService {

    private final RevokedTokenRepository repository;

    @Autowired
    public RevokedTokenService(RevokedTokenRepository repository) {
        this.repository = repository;
    }

    DbRevokedToken logout(DbRevokedToken dbRevokedToken) {
        return repository.save(dbRevokedToken);
    }

    public boolean isTokenRevoked(UserContext userContext) {

        UUID userId = userContext.getUserId();
        Date expirationDate = userContext.getExpirationDate();
        return repository.findOneByUserIdAndExpirationDate(userId, expirationDate) != null;
    }

    public void removeOutdatedEntries() {
        Date currentTime = new Date();
        List<DbRevokedToken> revokedTokenList = repository.findAll();

        for (DbRevokedToken dbRevokedToken:revokedTokenList) {
if (dbRevokedToken.getExpirationDate().before(currentTime)){
   repository.delete(dbRevokedToken);
}
        }
    }
}


