package com.intive.shopme.model.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "Address voivodeship", description = "Represents possible voivodeship to choose in address")
public class Voivodeship {

    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Represents unique voivodeship name", required = true, example = "WesternPomeranian")
    private String name;
}
