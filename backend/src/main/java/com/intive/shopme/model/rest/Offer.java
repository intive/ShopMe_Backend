package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.intive.shopme.validation.LinkInTextCheck;
import com.intive.shopme.validation.ProgressiveOfferLevelsCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

import static com.intive.shopme.config.AppConfig.OFFER_DESCRIPTION_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.OFFER_TITLE_MAX_LENGTH;

@Data
@ApiModel(value = "Offer", description = "Represents the offer created by user")
@ProgressiveOfferLevelsCheck
public class Offer {

    @ApiModelProperty(value = "Represents unique id number", position = 1, example = "5d214c01-95c3-4ec4-8f68-51dfb80b191c")
    private UUID id;

    @ApiModelProperty(value = "Represents offer's date of submitting (EPOCH time in milliseconds)", position = 2,
            example = "1520031600000")
    private Date date;

    @ApiModelProperty(value = "Represents offer's title", required = true, position = 3,
            example = "Odśnieżanie Niebuszewo")
    @NotEmpty(message = "Offer's title cannot be empty.")
    @Size(max = OFFER_TITLE_MAX_LENGTH, message = "Offer's title has too many characters.")
    private String title;

    @NotNull(message = "No category selected.")
    @ApiModelProperty(value = "Represents offer's category", required = true, position = 4)
    private Category category;

    @NotEmpty(message = "Base description cannot be empty.")
    @Size(max = OFFER_DESCRIPTION_MAX_LENGTH,
            message = "Offer's base description has too many characters (max " + OFFER_DESCRIPTION_MAX_LENGTH + ").")
    @LinkInTextCheck(message = "Offer's base description can't contain any urls/links.")
    @ApiModelProperty(value = "Represents offer's base description", required = true, position = 5,
            example = "Oferuję odśnieżanie powierzchni płaskich.")
    private String baseDescription;

    @NotNull(message = "Base price cannot be empty.")
    @ApiModelProperty(value = "Represents offer's base price", required = true, position = 6, example = "12.34")
    private Double basePrice;

    @Size(max = OFFER_DESCRIPTION_MAX_LENGTH,
            message = "Offer's extended level description has too many characters (max " +
                    OFFER_DESCRIPTION_MAX_LENGTH + ").")
    @LinkInTextCheck(message = "Offer's extended description can't contain any urls/links.")
    @ApiModelProperty(value = "Represents offer's extended level description", position = 7,
            example = "Oferuję odśnieżanie powierzchni płaskich. Profesjonalne narzędzia, " +
                    "wysoka jakość wykonania usługi oraz dogodne terminy.")
    private String extendedDescription;

    @ApiModelProperty(value = "Represents offer's extended level price", position = 8, example = "23.45")
    private Double extendedPrice;

    @Size(max = OFFER_DESCRIPTION_MAX_LENGTH,
            message = "Offer's extra level description has too many characters (max " +
                    OFFER_DESCRIPTION_MAX_LENGTH + ").")
    @LinkInTextCheck(message = "Offer's extra description can't contain any urls/links.")
    @ApiModelProperty(value = "Represents offer's extra level description", position = 9,
            example = "Oferuję odśnieżanie powierzchni płaskich. Profesjonalne narzędzia, " +
                    "wysoka jakość wykonania usługi oraz dogodne terminy. W lato wysokie rabaty;)")
    private String extraDescription;

    @ApiModelProperty(value = "Represents offer's extra level price", position = 10, example = "34.56")
    private Double extraPrice;

    @NotNull(message = "No user selected.")
    @ApiModelProperty(value = "Represents the user who submitted this offer", required = true, position = 11)
    @JsonProperty("user")
    private Owner owner;

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
}
