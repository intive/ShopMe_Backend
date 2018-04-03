package com.intive.shopme.service;

import com.intive.shopme.model.Category;
import com.intive.shopme.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public void add(Category category) {
        repository.save(category);
    }

    public Category getCategoryById(UUID id) {
        return repository.getOne(id);
    }

}
