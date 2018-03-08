package com.intive.shopme.model;

import io.swagger.annotations.ApiModelProperty;
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
public @Data class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @ApiModelProperty(value = "Represents user's name", required = true)
    private String name;
    @ApiModelProperty(value = "Represents user's email", required = true)
    private String email;
    @ApiModelProperty(value = "Represents user's phone number", required = true)
    private String phoneNumber;
    @ApiModelProperty(value = "Represents additional information typed by user")
    private String additionalInfo;
}