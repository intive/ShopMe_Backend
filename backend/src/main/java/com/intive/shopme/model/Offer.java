package com.intive.shopme.model;


import com.intive.shopme.base.Identifiable;
import com.intive.shopme.util.validation.validation.CategoryCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@ApiModel(value = "Offer", description = "Represents the offer created by user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Offer extends Identifiable {

    @ApiModelProperty(value = "Represents offer's date of submitting", position = 2,
            example = "1234567890")
    private final Long date = System.currentTimeMillis();

    @ApiModelProperty(value = "Represents offer's title", required = true, position = 3,
            example = "Odśnieżanie Niebuszewo")
    @Size(max = 30, message = "Offer's title has too many characters.")
    @NotNull(message = "Title cannot be empty.")
    private String title;

    @CategoryCheck
    @Valid
    @NotNull(message = "No category selected.")
    @ApiModelProperty(value = "Represents offer's category", required = true, position = 4)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Category category;

    @NotNull(message = "Base description cannot be empty.")
    @Size(max = 500, message = "Offer's base description has too many characters. Maximum is 500.")
    @ApiModelProperty(value = "Represents offer's base description", required = true, position = 5,
            example = "Oferuję odśnieżanie powierzchni płaskich.")
    private String baseDescription;

    @ApiModelProperty(value = "Represents offer's base price", required = true, position = 6, example = "10")
    private float basePrice;

    @Size(max = 500, message = "Offer's extended level description has too many characters. Maximum is 500.")
    @ApiModelProperty(value = "Represents offer's extended level description", position = 7,
            example = "Oferuję odśnieżanie powierzchni płaskich. Profesjonalne narzędzia, " +
                    "wysoka jakość wykonania usługi oraz dogodne terminy.")
    private String extendedDescription;

    @ApiModelProperty(value = "Represents offer's extended level price", position = 8, example = "20")
    private float extendedPrice;

    @Size(max = 500, message = "Offer's extra level description has too many characters. Maximum is 500.")
    @ApiModelProperty(value = "Represents offer's extra level description", position = 9,
            example = "Oferuję odśnieżanie powierzchni płaskich. Profesjonalne narzędzia, " +
                    "wysoka jakość wykonania usługi oraz dogodne terminy. W lato wysokie rabaty;)")
    private String extraDescription;

    @ApiModelProperty(value = "Represents offer's extra level price", position = 10, example = "30")
    private float extraPrice;

    @Valid
    @NotNull(message = "No user selected.")
    @ApiModelProperty(value = "Represents the user who submitted this offer", required = true, position = 11)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

}
