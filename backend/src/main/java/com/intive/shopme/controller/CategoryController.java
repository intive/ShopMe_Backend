package com.intive.shopme.controller;

import com.intive.shopme.model.Category;
import com.intive.shopme.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.intive.shopme.config.ApiUrl.CATEGORIES;
import static com.intive.shopme.config.AppConfiguration.SWAGGER_CREATED;
import static com.intive.shopme.config.AppConfiguration.SWAGGER_EXISTS;
import static com.intive.shopme.config.AppConfiguration.SWAGGER_SUCCESS;

@Validated
@RestController
@RequestMapping(value = CATEGORIES)
@Api(value = "category", description = "REST API for categories operations", tags = "Categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SWAGGER_SUCCESS),
    })
    @GetMapping
    @ApiOperation(value = "Returns all available categories")
    public List<Category> getAllCategories() {
        return service.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = SWAGGER_CREATED),
            @ApiResponse(code = 409, message = SWAGGER_EXISTS)
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Saves new category")
    public Category add(@RequestBody Category category) {
        return service.create(category);
    }
}
