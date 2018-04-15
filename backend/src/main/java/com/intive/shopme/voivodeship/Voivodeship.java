package com.intive.shopme.voivodeship;

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
@ApiModel(value= "Address voivodeship", description = "Represents possible voivodeship to choose in adress")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Voivodeship extends Identifiable {

    @ApiModelProperty(value = "Represents voivodeship name", required = true, position = 2, example = "zachodniopomorskie")
    @Column(unique = true)
    @NotEmpty
    private String name;
}
