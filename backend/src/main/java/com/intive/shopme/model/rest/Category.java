package com.intive.shopme.model.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@ApiModel(value = "Offer's category", description = "Represents different category types of an offer")
public class Category {

    @ApiModelProperty(value = "Represents unique id number", position = 1, example = "5d214c01-95c3-4ec4-8f68-51dfb80b191c")
    private UUID id;

    @NotEmpty
    @ApiModelProperty(value = "Represents category's name", required = true, position = 2, example = "inne")
    private String name;
}
