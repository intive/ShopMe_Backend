package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intive.shopme.validation.LinkInTextCheck;
import com.intive.shopme.validation.ProgressiveOfferLevelsCheck;
import com.intive.shopme.validation.SpecialCharacterCheck;
import com.intive.shopme.validation.WhiteSpaceTabulatorCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.intive.shopme.config.AppConfig.CITY_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.OFFER_DESCRIPTION_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.OFFER_TITLE_MAX_LENGTH;

@Data
@ApiModel(value = "Offer", description = "Represents the offer created by user")
@ProgressiveOfferLevelsCheck
public class OfferWrite {

    @ApiModelProperty(value = "Represents offer's title", required = true, position = 1,
            example = "Odśnieżanie Niebuszewo")
    @NotEmpty(message = "Offer's title cannot be empty.")
    @Size(max = OFFER_TITLE_MAX_LENGTH, message = "Offer's title has too many characters.")
    private String title;

    @NotEmpty(message = "No category selected.")
    @ApiModelProperty(value = "Represents offer's category", required = true, position = 2, example = "others")
    private String category;

    @NotEmpty(message = "Base description cannot be empty.")
    @Size(max = OFFER_DESCRIPTION_MAX_LENGTH,
            message = "Offer's base description has too many characters (max " + OFFER_DESCRIPTION_MAX_LENGTH + ").")
    @LinkInTextCheck(message = "Offer's base description can't contain any urls/links.")
    @ApiModelProperty(value = "Represents offer's base description", required = true, position = 3,
            example = "Oferuję odśnieżanie powierzchni płaskich.")
    private String baseDescription;

    @NotNull(message = "Base price cannot be empty.")
    @ApiModelProperty(value = "Represents offer's base price", required = true, position = 4, example = "12.34")
    private Double basePrice;

    @Size(max = OFFER_DESCRIPTION_MAX_LENGTH,
            message = "Offer's extended level description has too many characters (max " +
                    OFFER_DESCRIPTION_MAX_LENGTH + ").")
    @LinkInTextCheck(message = "Offer's extended description can't contain any urls/links.")
    @ApiModelProperty(value = "Represents offer's extended level description", position = 5,
            example = "Oferuję odśnieżanie powierzchni płaskich. Profesjonalne narzędzia, " +
                    "wysoka jakość wykonania usługi oraz dogodne terminy.")
    private String extendedDescription;

    @ApiModelProperty(value = "Represents offer's extended level price", required = true, position = 6, example = "23.45")
    private Double extendedPrice;

    @Size(max = OFFER_DESCRIPTION_MAX_LENGTH,
            message = "Offer's extra level description has too many characters (max " +
                    OFFER_DESCRIPTION_MAX_LENGTH + ").")
    @LinkInTextCheck(message = "Offer's extra description can't contain any urls/links.")
    @ApiModelProperty(value = "Represents offer's extra level description", required = true, position = 7,
            example = "Oferuję odśnieżanie powierzchni płaskich. Profesjonalne narzędzia, " +
                    "wysoka jakość wykonania usługi oraz dogodne terminy. W lato wysokie rabaty;)")
    private String extraDescription;

    @ApiModelProperty(value = "Represents offer's extra level price", required = true, position = 8, example = "34.56")
    private Double extraPrice;

    @NotEmpty
    @ApiModelProperty(value = "Represents offer's voivodeship", required = true, position = 9, example = "WesternPomeranian")
    private String voivodeship;

    @NotEmpty
    @Size(max = CITY_MAX_LENGTH, message = "City contains too much characters, maximum is " + CITY_MAX_LENGTH + ".")
    @SpecialCharacterCheck
    @WhiteSpaceTabulatorCheck
    @ApiModelProperty(value = "Represents offer's city", required = true, position = 10, example = "Szczecin")
    private String city;

    @JsonIgnore
    public boolean isExtendedPresent() {
        return StringUtils.isNotEmpty(extendedDescription) ||
                extendedPrice != null;
    }

    @JsonIgnore
    public boolean isExtraPresent() {
        return StringUtils.isNotEmpty(extraDescription) ||
                extraPrice != null;
    }

    @JsonIgnore
    public boolean isExtendedComplete() {
        return StringUtils.isNotEmpty(extendedDescription) &&
                extendedPrice != null;
    }

    @JsonIgnore
    public boolean isExtraComplete() {
        return StringUtils.isNotEmpty(extraDescription) &&
                extraPrice != null;
    }
}
