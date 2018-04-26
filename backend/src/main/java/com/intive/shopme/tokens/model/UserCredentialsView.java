package com.intive.shopme.tokens.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "User Credentials")
public class UserCredentialsView {

    @ApiModelProperty(example = "user@mail.com", position = 1, required = true)
    @NotNull
    @Email
    private final String email;

    @ApiModelProperty(example = "Password", position = 2, required = true)
    @NotNull
    private final String password;
}
