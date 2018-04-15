package com.intive.shopme.category;

import com.intive.shopme.category.model.db.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Category findByName(String name);

    Category findByTranslateKey(String translateKey);
}
