package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbExpiredToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface ExpiredTokenRepository extends JpaRepository<DbExpiredToken, String> {

    DbExpiredToken findOneByUserIdAndExpirationDate(UUID userId, Long expirationDate);
}

