package com.intive.shopme.category;

import com.intive.shopme.model.db.DbCategory;
import com.intive.shopme.validation.AlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    List<DbCategory> getAll() {
        return repository.findAll();
    }

    DbCategory create(DbCategory dbCategory) {
        checkExistence(dbCategory);
        return repository.save(dbCategory);
    }

    boolean getCategoryById(UUID id) {
        return repository.existsById(id);
    }

    private void checkExistence(DbCategory dbCategory) {
        var foundByName = repository.findByName(dbCategory.getName());
        if (foundByName != null) {
            throw new AlreadyExistException(foundByName + " - name already exist");
        }
    }
}
