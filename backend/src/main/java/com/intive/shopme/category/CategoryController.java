package com.intive.shopme.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intive.shopme.category.model.db.Category;
import com.intive.shopme.category.model.view.CategoryView;
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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.intive.shopme.configuration.api.ApiUrl.CATEGORIES;
import static com.intive.shopme.configuration.swagger.SwaggerApiInfoConfigurer.Operations.CREATED;
import static com.intive.shopme.configuration.swagger.SwaggerApiInfoConfigurer.Operations.EXISTS;
import static com.intive.shopme.configuration.swagger.SwaggerApiInfoConfigurer.Operations.SUCCESS;

@Validated
@RestController
@RequestMapping(value = CATEGORIES)
@Api(value = "category", description = "REST API for categories operations", tags = "Categories")
public class CategoryController {

    private final CategoryService service;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Returns all available categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS)
    })
    public List<CategoryView> getAllCategories() {
        return convertToView(service.getAll());
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Saves new category")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = CREATED),
            @ApiResponse(code = 409, message = EXISTS)
    })
    public CategoryView add(@RequestBody CategoryView categoryView) {
        final Category category = convertToModel(categoryView);
        final Category categoryCreated = service.create(category);
        return convertToView(categoryCreated);
    }

    private static CategoryView convertToView(final Category category) {
        return OBJECT_MAPPER.convertValue(category, CategoryView.class);
    }

    private List<CategoryView> convertToView(final Collection<Category> category) {
        return category.stream().map(CategoryController::convertToView).collect(Collectors.toList());
    }

    private Category convertToModel(final CategoryView categoryView) {
        return OBJECT_MAPPER.convertValue(categoryView, Category.class);
    }
}
