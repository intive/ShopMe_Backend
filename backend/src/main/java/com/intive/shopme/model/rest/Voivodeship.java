package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "Voivodeship", description = "Represents possible voivodeship name")
public class Voivodeship {

    @NotEmpty
    @ApiModelProperty(value = "Represents unique voivodeship name", required = true, example = "WesternPomeranian")
    private String name;

    @JsonCreator
    public Voivodeship(@JsonProperty("name") String name) {
        this.name = name;
    }
}
