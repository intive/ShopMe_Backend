package com.intive.shopme.category.validation;

import com.intive.shopme.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServiceUtil {

    private static ServiceUtil instance;

    @Autowired
    private CategoryService categoryService;

    @PostConstruct
    public void fillInstance() {
        instance = this;
    }

    public static CategoryService getCategoryService() {
        return instance.categoryService;
    }

}
