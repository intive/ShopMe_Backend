package com.intive.shopme.user.registration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.intive.shopme.config.ApiUrl.USERS;

@RestController
@RequestMapping(value = USERS)
@Api(value = "user", description = "REST API for users operations", tags = "Users")
class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation("Saves new user")
    public User add(@RequestBody User user) {
        user.setId(UUID.randomUUID());
        return service.createOrUpdate(user);
    }

    @ApiOperation(value = "Returns user by id (temporary endpoint, please confirm in next REST API specification before production use)")
    @GetMapping(value = "{id}")
    public User get(@PathVariable UUID id) {
        return service.get(id).hidePassword();
    }
}
