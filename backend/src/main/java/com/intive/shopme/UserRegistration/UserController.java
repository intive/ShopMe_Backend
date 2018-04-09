package com.intive.shopme.UserRegistration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.intive.shopme.config.ApiUrl.USERS;

@RestController
@RequestMapping(value = USERS)
@Api(value = "users", description = "REST API for users operation", tags = "Users")
class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation("Saves new users")
    public void add(@RequestBody Users users) {
        users.setId(UUID.randomUUID());
        userService.add(users);
    }
}
