package com.intive.shopme.category;

import com.intive.shopme.identifiable.Identifiable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
@ApiModel(value = "Offer's category", description = "Represents different category types of an offer")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Category extends Identifiable {

    @ApiModelProperty(value = "Represents category's name", required = true, position = 2, example = "inne")
    @Column(unique = true)
    @NotEmpty
    private String name;
}
