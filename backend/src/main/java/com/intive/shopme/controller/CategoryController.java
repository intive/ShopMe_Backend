package com.intive.shopme.controller;

import com.intive.shopme.model.Category;
import com.intive.shopme.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.intive.shopme.config.ApiUrl.CATEGORIES;

@Validated
@RestController
@RequestMapping(value = CATEGORIES)
@Api(value = "category", description = "REST API for categories operations", tags = "Categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Returns all available categories")
    public List<Category> getAllCategories() {
        return service.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 409, message = "Category already exist")
    })
    @PostMapping
    @ApiOperation(value = "Saves new category")
    public Category add(@RequestBody Category category) {
        return service.create(category);
    }

    @DeleteMapping(value = "{id}")
    //TODO delete it after manual testing
    public void delete(@PathVariable UUID id) {
        throw new RuntimeException("testing");
    }
}
