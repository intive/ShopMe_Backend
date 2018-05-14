package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intive.shopme.validation.SpecialCharacterCheck;
import com.intive.shopme.validation.WhiteSpaceTabulatorCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

import static com.intive.shopme.config.AppConfig.CITY_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.STREET_MIN_LENGTH;
import static com.intive.shopme.config.AppConfig.ZIP_CODE_FORMAT;
import static com.intive.shopme.config.AppConfig.ZIP_CODE_MAX_LENGTH;

@Data
@ApiModel(value = "Address data", description = "Represents data for address")
public class Address {

    @ApiModelProperty(value = "Represents unique id number", position = 1, example = "5d214c01-95c3-4ec4-8f68-51dfb80b191c")
    private UUID id;

    @NotEmpty
    @Size(min = STREET_MIN_LENGTH, message = "Street contains not enough characters, minimum is " + STREET_MIN_LENGTH + ".")
    @SpecialCharacterCheck
    @WhiteSpaceTabulatorCheck
    @ApiModelProperty(value = "Represents street name", required = true, position = 2, example = "Niepodległości")
    private String street;

    @NotEmpty
    @ApiModelProperty(value = "Represents home number", required = true, position = 3, example = "12/1")
    private String number;

    @NotEmpty
    @Size(max = CITY_MAX_LENGTH, message = "City contains too much characters, maximum is " + CITY_MAX_LENGTH + ".")
    @SpecialCharacterCheck
    @WhiteSpaceTabulatorCheck
    @ApiModelProperty(value = "Represents city", required = true, position = 4, example = "Szczecin")
    private String city;

    @NotEmpty
    @Size(max = ZIP_CODE_MAX_LENGTH, message = "ZIP code contains too much characters, maximum is " + ZIP_CODE_MAX_LENGTH + ".")
    @Pattern(regexp = ZIP_CODE_FORMAT, message = "Zip code should be in format XX-XXX and contain only digits.")
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
