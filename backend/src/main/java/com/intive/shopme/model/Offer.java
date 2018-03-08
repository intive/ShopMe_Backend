package com.intive.shopme.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Qba Walat
 * created on 06/03/2018
 */

@Entity
public @Data class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "Represents offer's unique id number", required = true)
    private Long id;
    @ApiModelProperty(value = "Represents offer's date of submitting", required = true)
    private String date;
    @ApiModelProperty(value = "Represents offer's title - passed by user", required = true)
    private String title;
    @ApiModelProperty(value = "Represents offer's category", required = true)
    private Category category;
    @ApiModelProperty(value = "Represents offer's basic bundle", required = true)
    @ManyToOne
    private Bundle basicBundle;
//    @ApiModelProperty(value = "Represents offer's premium bundle")
//    private Bundle premiumBundle;
//    @ApiModelProperty(value = "Represents offer's premium plus bundle")
//    private Bundle premiumPlusBundle;
    @ApiModelProperty(value = "Represents the user who submits this offer", required = true)
    @OneToOne
    private User user;

    public enum Category {
        BUDOWA, OGROD
    }
}