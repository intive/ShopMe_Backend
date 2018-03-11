package com.intive.shopme.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@ApiModel(value = "Offer's category", description = "Represents different category types of an offer")
@Data
public class CategoryView {

    @ApiModelProperty(value = "Represents category's id")
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ApiModelProperty(value = "Represents category's name")
    private String name;

}
