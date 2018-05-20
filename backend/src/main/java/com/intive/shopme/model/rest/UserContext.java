package com.intive.shopme.model.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
public class UserContext {

    @ApiModelProperty(value = "Represents unique id number", position = 1, example = "5d214c01-95c3-4ec4-8f68-51dfb80b191c")
    private final UUID userId;

    @ApiModelProperty(value = "Represents user's email", position = 2, example = "unknown@gmail.com")
    private final String email;

    @ApiModelProperty(value = "Represents user's roles", position = 3)
    private final Set<GrantedAuthority> authorities;

    @ApiModelProperty(value = "Represents user's token expiration date", position = 4)
    private final Date expirationDate;

}
