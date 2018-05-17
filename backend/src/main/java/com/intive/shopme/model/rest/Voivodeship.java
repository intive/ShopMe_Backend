package com.intive.shopme.model.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "Address voivodeship", description = "Represents possible voivodeship to choose in address")
@AllArgsConstructor
@NoArgsConstructor
public class Voivodeship {

    @NotEmpty
    @ApiModelProperty(value = "Represents unique voivodeship name", required = true, example = "WesternPomeranian")
    private String name;
}
