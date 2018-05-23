package com.intive.shopme.category;

import com.intive.shopme.common.ConvertibleController;
import com.intive.shopme.model.db.DbCategory;
import com.intive.shopme.model.rest.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.intive.shopme.config.ApiUrl.CATEGORIES;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.SUCCESS;

@RestController
@RequestMapping(value = CATEGORIES)
@Api(value = "category", description = "REST API for categories operations", tags = "Categories")
class CategoryController extends ConvertibleController<DbCategory, Category, Category> {

    private final CategoryService service;

    CategoryController(CategoryService service) {
        super(DbCategory.class, Category.class, Category.class);
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
}
