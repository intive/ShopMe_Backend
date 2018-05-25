package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface UserRepository extends JpaRepository<DbUser, UUID> {

    boolean existsByEmail(String email);

    DbUser findOneByEmail(String email);
}
