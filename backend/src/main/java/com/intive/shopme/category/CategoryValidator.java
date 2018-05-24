package com.intive.shopme.category;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {

    private final CategoryService categoryService;

    public CategoryValidator(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type == String.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        var categoryName = (String) target;
        if (!categoryService.exists(categoryName)) {
            errors.rejectValue("category","Category name not known: " + categoryName);
        }

    }
}
