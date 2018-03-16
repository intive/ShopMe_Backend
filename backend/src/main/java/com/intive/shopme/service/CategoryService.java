package com.intive.shopme.service;

import com.intive.shopme.model.Category;
import com.intive.shopme.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void add(Category category) {
        categoryRepository.save(category);
    }

}
