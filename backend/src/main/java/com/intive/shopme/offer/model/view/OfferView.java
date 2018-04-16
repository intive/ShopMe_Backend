package com.intive.shopme.offer.model.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intive.shopme.base.model.IdentifiableView;
import com.intive.shopme.category.model.view.CategoryView;
import com.intive.shopme.category.validation.CategoryCheck;
import com.intive.shopme.offer.validation.LinkInTextCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

import static com.intive.shopme.configuration.api.AppConfiguration.OFFER_DESCRIPTION_MAX_LENGTH;
import static com.intive.shopme.configuration.api.AppConfiguration.OFFER_TITLE_MAX_LENGTH;

@ApiModel(value = "Offer", description = "Represents the offer created by user")
@Data
@EqualsAndHashCode(callSuper = true)
public class OfferView extends IdentifiableView {

    @ApiModelProperty(value = "Represents offer's date of submitting (EPOCH time in milliseconds)", position = 2,
            example = "1520031600000")
    private Date date;

    @ApiModelProperty(value = "Represents offer's title", required = true, position = 3,
            example = "Odśnieżanie Niebuszewo")
    @NotNull(message = "Offer's title cannot be empty.")
    @Size(max = OFFER_TITLE_MAX_LENGTH, message = "Offer's title has too many characters.")
    private String title;

    @CategoryCheck
    @Valid
    @NotNull(message = "No category selected.")
    @ApiModelProperty(value = "Represents offer's category", required = true, position = 4)
    private CategoryView category;

    @NotNull(message = "Base description cannot be empty.")
    @Size(max = OFFER_DESCRIPTION_MAX_LENGTH,
            message = "Offer's base description has too many characters (max " + OFFER_DESCRIPTION_MAX_LENGTH + ").")
    @LinkInTextCheck(message = "Offer's base description can't contain any urls/links.")
    @ApiModelProperty(value = "Represents offer's base description", required = true, position = 5,
            example = "Oferuję odśnieżanie powierzchni płaskich.")
    private String baseDescription;

    @NotNull(message = "Base price cannot be empty.")
    @ApiModelProperty(value = "Represents offer's base price", required = true, position = 6, example = "12.34")
    private Float basePrice;

    @Size(max = OFFER_DESCRIPTION_MAX_LENGTH,
            message = "Offer's extended level description has too many characters (max " +
                    OFFER_DESCRIPTION_MAX_LENGTH + ").")
    @LinkInTextCheck(message = "Offer's extended description can't contain any urls/links.")
    @ApiModelProperty(value = "Represents offer's extended level description", position = 7,
            example = "Oferuję odśnieżanie powierzchni płaskich. Profesjonalne narzędzia, " +
                    "wysoka jakość wykonania usługi oraz dogodne terminy.")
    private String extendedDescription;

    @ApiModelProperty(value = "Represents offer's extended level price", position = 8, example = "23.45")
    private Float extendedPrice;

    @Size(max = OFFER_DESCRIPTION_MAX_LENGTH,
            message = "Offer's extra level description has too many characters (max " +
                    OFFER_DESCRIPTION_MAX_LENGTH + ").")
    @LinkInTextCheck(message = "Offer's extra description can't contain any urls/links.")
    @ApiModelProperty(value = "Represents offer's extra level description", position = 9,
            example = "Oferuję odśnieżanie powierzchni płaskich. Profesjonalne narzędzia, " +
                    "wysoka jakość wykonania usługi oraz dogodne terminy. W lato wysokie rabaty;)")
    private String extraDescription;

    @ApiModelProperty(value = "Represents offer's extra level price", position = 10, example = "34.56")
    private Float extraPrice;

    @Valid
    @NotNull(message = "No user selected.")
    @ApiModelProperty(value = "Represents the user who submitted this offer", required = true, position = 11)
    @JsonProperty("user")
    private OwnerView owner;
}
