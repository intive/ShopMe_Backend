package com.intive.shopme.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@ApiModel
@Builder
public @Data
class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "Represents offer's unique id number", required = true)
    @Getter
    @Setter
    private Long id;

    @ApiModelProperty(value = "Represents offer's date of submitting", required = true)
    @Getter
    @Setter
    private String date;

    @ApiModelProperty(value = "Represents offer's title - passed by user", required = true)
    @Getter
    @Setter
    private String title;

    /* @ApiModelProperty(value = "Represents offer's category", required = true)
     @Getter
     @Setter
     private Category category;
    */
    @ApiModelProperty(value = "Represents offer's bundle", required = true)
    @ManyToOne
    @Getter
    @Setter
    private Bundle bundle;

    @ApiModelProperty(value = "Represents the user who submits this offer", required = true)
    @OneToOne
    @Getter
    @Setter
    private User user;

}