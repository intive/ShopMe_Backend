package com.intive.shopme.model;

import com.intive.shopme.base.Identifiable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@ApiModel(value = "Offer's category", description = "Represents different category types of an offer")
@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends Identifiable {

    @ApiModelProperty(value = "Represents category's name", required = true, position = 2, example = "inne")
    @Column(unique = true)
    private String name;

    @ApiModelProperty(value = "Represents key for translate operation", required = true, position = 3, example = "others")
    @Column(unique = true)
    private String translateKey;
}
