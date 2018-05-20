package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "User ID and JWT token expiration time")
@Builder
@AllArgsConstructor
public class RevokedToken {
    @NotEmpty
    @ApiModelProperty(example = "c5296892-347f-4b2e-b1c6-6faff971f767", position = 1)
    private final UUID userId;

    @ApiModelProperty(example = "1520031600000", position = 2)
    private final Date expirationDate;
}

