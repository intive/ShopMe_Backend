package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value= "Address voivodeship", description = "Represents possible voivodeship to choose in adress")
public class Voivodeship extends Identifiable {

    @NotEmpty
    @ApiModelProperty(value = "Represents voivodeship name", required = true, position = 2, example = "WesternPomeranian")
    private String name;

    @JsonIgnore
    public boolean isFilled() {
        return !StringUtils.isEmpty(name);
    }
}