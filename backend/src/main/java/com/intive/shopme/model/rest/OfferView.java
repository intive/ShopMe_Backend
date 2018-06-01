package com.intive.shopme.model.rest;

import com.intive.shopme.validation.ProgressiveOfferLevelsCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@ApiModel(value = "Offer", description = "Represents the offer created by user")
@ProgressiveOfferLevelsCheck
public class OfferView {

    @ApiModelProperty(value = "Represents unique id number", required = true, position = 1, example = "5d214c01-95c3-4ec4-8f68-51dfb80b191c")
    private UUID id;

    @ApiModelProperty(value = "Represents offer's date of submitting (EPOCH time in milliseconds)", required = true,
            position = 2, example = "1520031600000")
    private Date date;

    @ApiModelProperty(value = "Represents offer's title", required = true, position = 3,
            example = "Odśnieżanie Niebuszewo")
    private String title;

    @ApiModelProperty(value = "Represents offer's category", required = true, position = 4, example = "others")
    private String category;

    @ApiModelProperty(value = "Represents offer's base description", required = true, position = 5,
            example = "Oferuję odśnieżanie powierzchni płaskich.")
    private String baseDescription;

    @ApiModelProperty(value = "Represents offer's base price", required = true, position = 6, example = "12.34")
    private Double basePrice;

    @ApiModelProperty(value = "Represents offer's extended level description", position = 7,
            example = "Oferuję odśnieżanie powierzchni płaskich. Profesjonalne narzędzia, " +
                    "wysoka jakość wykonania usługi oraz dogodne terminy.")
    private String extendedDescription;

    @ApiModelProperty(value = "Represents offer's extended level price", position = 8, example = "23.45")
    private Double extendedPrice;

    @ApiModelProperty(value = "Represents offer's extra level description", position = 9,
            example = "Oferuję odśnieżanie powierzchni płaskich. Profesjonalne narzędzia, " +
                    "wysoka jakość wykonania usługi oraz dogodne terminy. W lato wysokie rabaty;)")
    private String extraDescription;

    @ApiModelProperty(value = "Represents offer's extra level price", position = 10, example = "34.56")
    private Double extraPrice;

    @ApiModelProperty(value = "Represents offer user's ID", required = true, position = 11,
            example = "5d214c01-95c3-4ec4-8f68-51dfb80b191c")
    private UUID user;

    @ApiModelProperty(value = "Represents user's name", required = true, position = 12, example = "Jan")
    private String name;

    @ApiModelProperty(value = "Represents user's surname", required = true, position = 13, example = "Kowalski")
    private String surname;

    @ApiModelProperty(value = "Represents user's email", required = true, position = 14, example = "unknown@gmail.com")
    private String email;

    @ApiModelProperty(value = "Represents user's phone number", required = true, position = 15, example = "0234567890")
    private String phoneNumber;

    @ApiModelProperty(value = "Represents offer's voivodeship", required = true, position = 16,
            example = "WestPomeranian")
    private String voivodeship;

    @ApiModelProperty(value = "Represents offer's city", required = true, position = 17, example = "Szczecin")
    private String city;

    @ApiModelProperty(value = "Represents additional information typed by user", position = 18,
            example = "Dodatkowe info")
    private String additionalInfo;
}
