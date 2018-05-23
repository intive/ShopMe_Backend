package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "User info and JWT token")
@Builder
public class Token {

    @ApiModelProperty(example = "c5296892-347f-4b2e-b1c6-6faff971f767", position = 1)
    private final UUID userId;

    @ApiModelProperty(example = "unknown@gmail.com", position = 2)
    private final String email;

    @ApiModelProperty(position = 3)
    private final String name;

    @ApiModelProperty(position = 4)
    private final String surname;

    @ApiModelProperty(position = 5)
    private final Set<Role> roles;

    @ApiModelProperty(example = "1520031600000", position = 6)
    private final Date expirationDate;

    @ApiModelProperty(position = 7)
    private final String jwt;
}

