package com.intive.shopme.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
public @Data
class Bundle {

    @ApiModelProperty(value = "Represents bundle's id number")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @ApiModelProperty(value = "Represents bundle's description")
    @Getter
    @Setter
    private String description;

    @ApiModelProperty(value = "Represents bundle's price")
    @Getter
    @Setter
    private String price;
}