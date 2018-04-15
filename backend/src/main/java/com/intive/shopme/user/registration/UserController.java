package com.intive.shopme.user.registration;

import com.intive.shopme.user.registration.model.db.User;
import com.intive.shopme.user.registration.model.view.UserView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.intive.shopme.configuration.api.ApiUrl.USERS;
import static com.intive.shopme.configuration.swagger.SwaggerApiInfoConfigurer.Operations.CREATED;
import static com.intive.shopme.configuration.swagger.SwaggerApiInfoConfigurer.Operations.NOT_FOUND;
import static com.intive.shopme.configuration.swagger.SwaggerApiInfoConfigurer.Operations.SUCCESS;

@RestController
@RequestMapping(value = USERS)
@Api(value = "user", description = "REST API for users operations", tags = "Users")
class UserController {

    private final UserService service;

    @Autowired
    private ModelMapper modelMapper;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = CREATED),
    })
    @ApiOperation("Saves new user")
    public UserView add(@RequestBody UserView userView) {
        final User user = convertToModel(userView);
        user.setId(UUID.randomUUID());
        final User userCreated = service.createOrUpdate(user);
        return convertToView(userCreated);
    }

    @GetMapping(value = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 404, message = NOT_FOUND)
    })
    @ApiOperation(value = "Returns user by id (temporary endpoint, please confirm in next REST API specification before production use)")
    public UserView get(@PathVariable UUID id) {
        return convertToView(service.get(id).hidePassword());
    }

    private UserView convertToView(final User user) {
        return modelMapper.map(user, UserView.class);
    }

    private User convertToModel(final UserView userView) {
        return modelMapper.map(userView, User.class);
    }

    // TODO temporary solution, need to be change/discuss - public method to check if email exist in database should not be avalible
    @GetMapping(value = "/email={email}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 404, message = NOT_FOUND)
    })
    @ApiOperation(value = "Search if user with specified email exist in database")
    public boolean getByEmail(@PathVariable String email) {
        return service.findIfUserExist(email);
    }
}
