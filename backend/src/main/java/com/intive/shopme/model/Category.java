package com.intive.shopme.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@ApiModel(value = "Offer's category", description = "Represents different category types of an offer")
@Data
public class Category {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @ApiModelProperty(value = "Represents category's id", position = 1, example = "22222222-2222-2222-2222-222222222222")
    private UUID id;

    @ApiModelProperty(value = "Represents category's name", required = true, example = "Inne", position = 2)
    private String name;

}
