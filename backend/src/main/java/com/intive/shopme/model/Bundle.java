package com.intive.shopme.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ApiModel(value = "Offer's Bundle", description = "Represents different bundle types of an offer")
@Builder
public @Data
class Bundle {

    @ApiModelProperty(value = "Represents bundle's id number")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Represents bundle's description")
    private String description;

    @ApiModelProperty(value = "Represents bundle's price")
    private String price;
}