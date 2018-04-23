package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface UserRepository extends JpaRepository<DbUser, UUID> {

    List<DbUser> findUserByEmail(String email);
}
