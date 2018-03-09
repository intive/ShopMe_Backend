package com.intive.shopme.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    @Getter
    @Setter
    private Long id;

    @ApiModelProperty(value = "Represents category's name")
    @Getter
    @Setter
    private String name;
}
