package com.intive.shopme.voivodeship;

import com.intive.shopme.model.db.DbVoivodeship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface VoivodeshipRepository extends JpaRepository<DbVoivodeship, String> {

    Boolean existsByName(String name);
}
