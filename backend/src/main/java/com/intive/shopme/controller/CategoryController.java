package com.intive.shopme.controller;

import com.intive.shopme.model.Category;
import com.intive.shopme.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@Api(value = "category", description = "REST API for categories operations", tags = "Categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @ApiOperation(value = "Returns all available categories")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @PostMapping
    @ApiOperation(value = "Saves new category")
    public void add(@RequestBody Category category) {
        categoryService.add(category);
    }

}
