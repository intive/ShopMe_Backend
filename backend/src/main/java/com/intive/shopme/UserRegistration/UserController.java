package com.intive.shopme.UserRegistration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.intive.shopme.config.ApiUrl.USERS;

@RestController
@RequestMapping(value = USERS)
@Api(value = "user", description = "REST API for users operations", tags = "Users")
class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation("Saves new user")
    public Users add(@RequestBody Users user) {
        user.setId(UUID.randomUUID());
        return service.add(user);
    }

    @ApiOperation(value = "Returns user by id (temporary endpoint, please confirm in next REST API specification before production use)")
    @GetMapping(value = "{id}")
    public Users get(@PathVariable UUID id) {
        Users result = service.get(id);
        if (result != null) {
            return result.hidePassword();
        } else {
            return null;
        }
    }

}
