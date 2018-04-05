package com.intive.shopme.UserRegistration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface UserRepository extends JpaRepository<Users, UUID>, JpaSpecificationExecutor<Users> {
}
