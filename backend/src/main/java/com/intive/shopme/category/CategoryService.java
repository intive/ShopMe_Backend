package com.intive.shopme.category;

import com.intive.shopme.model.db.DbCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    List<DbCategory> getAll() {
        return repository.findAll();
    }

    boolean exists(String categoryName) {
        return repository.existsByName(categoryName);
    }
}
