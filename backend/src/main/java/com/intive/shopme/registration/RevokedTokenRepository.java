package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbRevokedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
interface RevokedTokenRepository extends JpaRepository<DbRevokedToken, UUID> {

    DbRevokedToken findOneByUserIdAndExpirationDate(UUID userId, Date expirationDate);
    List<DbRevokedToken>findAll();

    @Override
    void delete(DbRevokedToken dbRevokedToken);
}

