package com.intive.shopme.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@ApiModel(value = "Offer", description = "Represents the offer created by user")
@Builder
public @Data
class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "Represents offer's unique id number", required = true)
    private Long id;

    @ApiModelProperty(value = "Represents offer's date of submitting", required = true)
    private String date;

    @ApiModelProperty(value = "Represents offer's title - passed by user", required = true)
    private String title;

    @ApiModelProperty(value = "Represents offer's category", required = true)
    @OneToOne
    private Category category;

    @ApiModelProperty(value = "Represents offer's bundle", required = true)
    @ManyToOne
    private Bundle bundle;

    @ApiModelProperty(value = "Represents the user who submits this offer", required = true)
    @OneToOne
    private User user;

}