package com.intive.shopme.model.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@ApiModel(value = "Address voivodeship", description = "Represents possible voivodeship to choose in address")
public class Voivodeship {

    @ApiModelProperty(value = "Represents unique id number", position = 1, example = "1511273a-bb97-4e8a-924b-e6ff7583f135")
    private UUID id;

    @NotEmpty
    @ApiModelProperty(value = "Represents voivodeship name", required = true, position = 2, example = "WesternPomeranian")
    private String name;
}
