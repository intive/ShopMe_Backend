package com.intive.shopme.category;

import com.intive.shopme.model.rest.Offer;
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
        return type == Offer.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target != null) {
            var category = ((Offer) target).getCategory();
            if (category != null) {
                var name = category.getName();
                if (!categoryService.checkCategoryExistenceByName(name)) {
                    errors.rejectValue("category.name", "name.invalid", "Category name not known: " + name);
                }
            }
        }
    }
}
