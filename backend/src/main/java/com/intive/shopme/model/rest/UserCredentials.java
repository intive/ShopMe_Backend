package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "User Credentials")
public class UserCredentials {

    @NotEmpty
    @Email
    @ApiModelProperty(value = "Represents user's email", example = "unknown@gmail.com", required = true, position = 1)
    private final String email;

    @NotEmpty
    @ApiModelProperty(value = "Represents user's password", example = "Password9", required = true, position = 2)
    private final String password;

    @JsonCreator
    public UserCredentials(@JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }
}
