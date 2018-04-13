package com.intive.shopme.user.registration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.intive.shopme.config.ApiUrl.USERS;
import static com.intive.shopme.config.AppConfiguration.SWAGGER_NOT_FOUND;

@RestController
@RequestMapping(value = USERS)
@Api(value = "user", description = "REST API for users operations", tags = "Users")
class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New user successfully created"),
    })
    @PostMapping
    @ApiOperation("Saves new user")
    public User add(@RequestBody User user) {
        user.setId(UUID.randomUUID());
        return service.createOrUpdate(user);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 404, message = SWAGGER_NOT_FOUND)
    })
    @ApiOperation(value = "Returns user by id (temporary endpoint, please confirm in next REST API specification before production use)")
    @GetMapping(value = "{id}")
    public User get(@PathVariable UUID id) {
        return service.get(id).hidePassword();
    }
}
