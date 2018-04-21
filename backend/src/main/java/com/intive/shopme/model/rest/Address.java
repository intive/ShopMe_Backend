package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@Data
@ApiModel(value = "Adress data", description = "Represents data for adress")
public class Address {

    @ApiModelProperty(value = "Represents unique id number", position = 1, example = "5d214c01-95c3-4ec4-8f68-51dfb80b191c")
    private UUID id;

    @ApiModelProperty(value = "Represents street name", required = true, position = 2, example = "Niepodległości")
    private String street;

    @ApiModelProperty(value = "Represents home number", required = true, position = 3, example = "12/1")
    private String number;

    @ApiModelProperty(value = "Represents city", required = true, position = 4, example = "Szczecin")
    private String city;

    @ApiModelProperty(value = "Represents ZIP code", required = true, position = 5, example = "70-125")
    private String zipCode;

    @JsonIgnore
    public boolean isFilled() {
        return !StringUtils.isEmpty(street)
                && !StringUtils.isEmpty(number)
                && !StringUtils.isEmpty(city)
                && !StringUtils.isEmpty(zipCode);
    }
}
