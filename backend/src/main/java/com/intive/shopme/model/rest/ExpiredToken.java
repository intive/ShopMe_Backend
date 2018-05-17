package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "User ID and JWT token expiration time")
@Builder
public class ExpiredToken {

    @ApiModelProperty(example = "c5296892-347f-4b2e-b1c6-6faff971f767", position = 1)
    private final String userId;


    @ApiModelProperty(example = "1520031600000", position = 2)
    private final Date expirationDate;
}

