package com.intive.shopme.model.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "Offer's category", description = "Represents different category types of an offer")
public class Category extends Identifiable {

    @NotEmpty
    @ApiModelProperty(value = "Represents category's name", required = true, position = 2, example = "inne")
    private String name;
}
