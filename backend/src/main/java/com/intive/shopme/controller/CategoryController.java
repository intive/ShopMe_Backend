package com.intive.shopme.controller;

import com.intive.shopme.model.Category;
import com.intive.shopme.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.intive.shopme.config.ApiUrl.CATEGORIES_PATH;

@RestController
@RequestMapping(value = CATEGORIES_PATH)
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
