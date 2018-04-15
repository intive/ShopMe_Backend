package com.intive.shopme.voivodeship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface VoivodeshipRepository extends JpaRepository<Voivodeship, UUID> {

}
