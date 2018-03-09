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

@Entity
@ApiModel
@Builder
public @Data
class User {

    @ApiModelProperty(value = "Represents user's id number")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @ApiModelProperty(value = "Represents user's name", required = true)
    @Getter
    @Setter
    private String name;

    @ApiModelProperty(value = "Represents user's email", required = true)
    @Getter
    @Setter
    private String email;

    @ApiModelProperty(value = "Represents user's phone number", required = true)
    @Getter
    @Setter
    private String phoneNumber;

    @ApiModelProperty(value = "Represents additional information typed by user")
    @Getter
    @Setter
    private String additionalInfo;
}