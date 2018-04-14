package com.intive.shopme.category;

import com.intive.shopme.exception.AlreadyExistException;
import com.intive.shopme.validator.Validated;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService extends Validated<Category> {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category create(Category category) {
        checkExistence(category);
        validate(category);
        return repository.save(category);
    }

    public Category getCategoryById(UUID id) {
        return repository.getOne(id);
    }

    private void checkExistence(Category category) {
        var foundByName = repository.findByName(category.getName());
        if (foundByName != null) {
            throw new AlreadyExistException(foundByName + " - name already exist");
        }
        var foundByTranslateKey = repository.findByTranslateKey(category.getTranslateKey());
        if (foundByTranslateKey != null) {
            throw new AlreadyExistException(foundByTranslateKey + " - translate key already exist");
        }
    }
}
