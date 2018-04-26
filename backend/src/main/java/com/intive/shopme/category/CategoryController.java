package com.intive.shopme.category;

import com.intive.shopme.common.ConvertibleController;
import com.intive.shopme.model.db.DbCategory;
import com.intive.shopme.model.rest.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.intive.shopme.config.ApiUrl.CATEGORIES;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.CREATED;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.EXISTS;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.SUCCESS;

@RestController
@RequestMapping(value = CATEGORIES)
@Api(value = "category", description = "REST API for categories operations", tags = "Categories")
class CategoryController extends ConvertibleController<DbCategory, Category> {

    private final CategoryService service;

    CategoryController(CategoryService service) {
        super(DbCategory.class, Category.class);
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Returns all available categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS)
    })
    List<Category> getAllCategories() {
        return convertToView(service.getAll());
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Saves new category")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = CREATED),
            @ApiResponse(code = 409, message = EXISTS)
    })
    Category add(@Valid @RequestBody Category category) {
        category.setId(UUID.randomUUID());
        var dbCategory = convertToDbModel(category);
        return convertToView(service.create(dbCategory));
    }
}
