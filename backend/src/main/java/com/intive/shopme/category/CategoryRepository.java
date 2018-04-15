package com.intive.shopme.category;

import com.intive.shopme.model.db.DbCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface CategoryRepository extends JpaRepository<DbCategory, UUID> {

    DbCategory findByName(String name);
}
