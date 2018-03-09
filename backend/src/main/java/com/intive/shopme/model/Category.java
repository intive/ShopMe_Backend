package com.intive.shopme.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@ApiModel
@Builder
public @Data
class Category {

    @ApiModelProperty(value = "Represents category's id")
    @Id
    @GeneratedValue
    private Long id;

    @ApiModelProperty(value = "Represents category's name")
    private String name;
}
