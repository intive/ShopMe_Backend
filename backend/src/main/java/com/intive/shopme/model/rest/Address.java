package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Adress data", description = "Represents data for adress")
public class Address extends Identifiable {

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
