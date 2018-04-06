package com.intive.shopme.service;

import com.intive.shopme.model.Category;
import com.intive.shopme.repository.CategoryRepository;
import com.intive.shopme.util.validation.error.AlreadyExistException;
import com.intive.shopme.util.validation.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.intive.shopme.config.AppConfig.CATEGORY_NOT_FOUND;

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
        if (repository.existsByName(category.getName())) {
            throw new AlreadyExistException("Category with this name already exist");
        }
        if (repository.existsByTranslateKey(category.getTranslateKey())) {
            throw new AlreadyExistException("Category with this translateKey already exist");
        }
        repository.save(category);
    }

    public Category getCategoryById(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(CATEGORY_NOT_FOUND);
        }
        return repository.getOne(id);
    }

}
