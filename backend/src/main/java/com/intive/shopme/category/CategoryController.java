package com.intive.shopme.category;

import com.intive.shopme.category.model.db.Category;
import com.intive.shopme.category.model.view.CategoryView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @Autowired
    private ModelMapper modelMapper;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Returns all available categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS)
    })
    public List<CategoryView> getAllCategories() {
        List<Category> categories = service.getAll();
        return convertToView(categories);
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

    private CategoryView convertToView(final Category category) {
        return modelMapper.map(category, CategoryView.class);
    }

    private List<CategoryView> convertToView(final Collection<Category> category) {
        List<CategoryView> categoryViews = new ArrayList<>();
        category.forEach(
                object -> categoryViews.add(convertToView(object))
        );
        return categoryViews;
    }

    private Category convertToModel(final CategoryView categoryView) {
        return modelMapper.map(categoryView, Category.class);
    }
}
