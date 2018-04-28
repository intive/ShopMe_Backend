package com.intive.shopme.model.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "Address voivodeship", description = "Represents possible voivodeship to choose in address")
public class Voivodeship {

    @NotEmpty
    @ApiModelProperty(value = "Represents voivodeship name", required = true, position = 2, example = "WesternPomeranian")
    private String name;
}
