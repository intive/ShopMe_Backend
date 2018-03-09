package com.intive.shopme.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Qba Walat
 * created on 06/03/2018
 */
@Entity
@ApiModel
@Builder
public @Data class Bundle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @ApiModelProperty(value = "Represents bundle's description")
    private String description;
    @ApiModelProperty(value = "Represents bundle's price")
    private String price;
}