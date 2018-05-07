package com.intive.shopme.model.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "Offer's category", description = "Represents different category types of an offer")
public class Category {

    @NotEmpty
    @ApiModelProperty(value = "Represents unique category's name", required = true, example = "others")
    private String name;
}
