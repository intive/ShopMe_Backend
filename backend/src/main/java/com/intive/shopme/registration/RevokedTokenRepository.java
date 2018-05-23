package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbRevokedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
interface RevokedTokenRepository extends JpaRepository<DbRevokedToken, UUID> {

    DbRevokedToken findOneByUserIdAndExpirationDate(UUID userId, Date expirationDate);

    List<DbRevokedToken> findAll();

    void delete(DbRevokedToken dbRevokedToken);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM REVOKED_TOKEN WHERE EXPIRATION_DATE < CURRENT_TIMESTAMP", nativeQuery = true)
    void removeExpiredTokens();
}
