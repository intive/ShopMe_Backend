package com.intive.shopme.user.registration;

import com.intive.shopme.user.registration.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface UserRepository extends JpaRepository<User, UUID> {

    User findUserByEmail(String email);
}
