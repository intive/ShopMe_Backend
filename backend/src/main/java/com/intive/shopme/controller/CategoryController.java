package com.intive.shopme.controller;

import com.intive.shopme.model.Category;
import com.intive.shopme.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.intive.shopme.config.ApiUrl.CATEGORIES;

@RestController
@RequestMapping(value = CATEGORIES)
@Api(value = "category", description = "REST API for categories operations", tags = "Categories")
public class CategoryController {

    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Returns all available categories")
    public List<Category> getAllCategories() {
        return service.getAll();
    }

    @PostMapping
    @ApiOperation(value = "Saves new category")
    public void add(@RequestBody Category category) {
        service.add(category);
    }

}
