package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "User Credentials")
public class UserCredentials {

    @ApiModelProperty(example = "unknown@gmail.com", position = 1, required = true)
    @NotNull
    @Email
    private final String email;

    @ApiModelProperty(example = "password", position = 2, required = true)
    @NotNull
    private final String password;

    @JsonCreator
    public UserCredentials(@JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }
}
