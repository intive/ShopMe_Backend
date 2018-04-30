package com.intive.shopme.tokens;

import com.intive.shopme.model.db.DbUser;
import com.intive.shopme.registration.UserService;
import com.intive.shopme.tokens.model.JwtView;
import com.intive.shopme.tokens.model.UserContext;
import com.intive.shopme.tokens.model.UserCredentialsView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

import static com.intive.shopme.config.ApiUrl.CURRENT_USER;
import static com.intive.shopme.config.ApiUrl.LOGIN;

@Api(tags = "Login", description = "Log in to application")
@RestController
public class TokenController {

    private final TokenService tokensService;
    private final UserService userService;
    private final JwtParser jwtParser;

    @Autowired
    TokenController(TokenService tokensService, UserService userService, JwtParser jwtParser) {
        this.tokensService = tokensService;
        this.userService = userService;
        this.jwtParser = jwtParser;
    }

    @ApiOperation("Log in to api")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User info and JWT token"),
            @ApiResponse(code = 401, message = "Incorrect user or password")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = LOGIN, consumes = MediaType.APPLICATION_JSON_VALUE)
    public JwtView login(@Valid @RequestBody UserCredentialsView credentials) {

        final DbUser user = userService.findOneByEmail(credentials.getEmail());
        final Boolean isAuthenticated = tokensService.isUserAuthenticated(user, credentials.getPassword());
        if (!isAuthenticated) {
            throw new BadCredentialsException("Incorrect email or password");
        }

        final String token = tokensService.getToken(user);
        jwtParser.parse(token);

        return JwtView.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .roles(user.getRoles())
                .expirationDateSeconds(jwtParser.getTokenExpirationDate().toInstant().getEpochSecond())
                .jwt(token)
                .build();
    }

    @ApiOperation(value = "Show current user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = CURRENT_USER)
    @PreAuthorize("hasAnyAuthority('SA', 'USER')")
    public UserContext whoAmI(@ApiIgnore @AuthenticationPrincipal UserContext userContext) {
        return userContext;
    }
}