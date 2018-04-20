package com.intive.shopme.voivodeship;

import com.intive.shopme.model.db.DbVoivodeship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface VoivodeshipRepository extends JpaRepository<DbVoivodeship, UUID> {
}
