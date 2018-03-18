package com.intive.shopme.model;

import com.intive.shopme.base.Identifiable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@ApiModel(value = "Offer's category", description = "Represents different category types of an offer")
@Data
public class Category extends Identifiable {

    @ApiModelProperty(value = "Represents category's name", required = true, example = "Inne", position = 2)
    private String name;

}
