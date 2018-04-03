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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.Instant;

import static com.intive.shopme.config.AppConfig.OFFER_DESCRIPTION_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.OFFER_TITLE_MAX_LENGTH;

@Entity
@ApiModel(value = "Offer", description = "Represents the offer created by user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Offer extends Identifiable {

    @ApiModelProperty(value = "Represents offer's date of submitting (EPOCH time in milliseconds)", position = 2,
            example = "1520031600000")
    private Long date = Instant.now().toEpochMilli();

    @ApiModelProperty(value = "Represents offer's title", required = true, position = 3,
            example = "Odśnieżanie Niebuszewo")
    @NotNull(message = "Offer's title cannot be empty.")
    @Size(max = OFFER_TITLE_MAX_LENGTH, message = "Offer's title has too many characters.")
    private String title;

    @CategoryCheck
    @Valid
    @NotNull(message = "No category selected.")
    @ApiModelProperty(value = "Represents offer's category", required = true, position = 4)
    @ManyToOne
    @JoinTable(name = "offer_category",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Category category;

    @NotNull(message = "Base description cannot be empty.")
    @Size(max = OFFER_DESCRIPTION_MAX_LENGTH,
            message = "Offer's base description has too many characters (max " + OFFER_DESCRIPTION_MAX_LENGTH + ").")
    @ApiModelProperty(value = "Represents offer's base description", required = true, position = 5,
            example = "Oferuję odśnieżanie powierzchni płaskich.")
    private String baseDescription;

    @NotNull(message = "Base price cannot be empty.")
    @ApiModelProperty(value = "Represents offer's base price", required = true, position = 6, example = "12.34")
    private Float basePrice;

    @Size(max = OFFER_DESCRIPTION_MAX_LENGTH,
            message = "Offer's extended level description has too many characters (max " +
                    OFFER_DESCRIPTION_MAX_LENGTH + ").")
    @ApiModelProperty(value = "Represents offer's extended level description", position = 7,
            example = "Oferuję odśnieżanie powierzchni płaskich. Profesjonalne narzędzia, " +
                    "wysoka jakość wykonania usługi oraz dogodne terminy.")
    private String extendedDescription;

    @ApiModelProperty(value = "Represents offer's extended level price", position = 8, example = "23.45")
    private Float extendedPrice;

    @Size(max = OFFER_DESCRIPTION_MAX_LENGTH,
            message = "Offer's extra level description has too many characters (max " +
                    OFFER_DESCRIPTION_MAX_LENGTH + ").")
    @ApiModelProperty(value = "Represents offer's extra level description", position = 9,
            example = "Oferuję odśnieżanie powierzchni płaskich. Profesjonalne narzędzia, " +
                    "wysoka jakość wykonania usługi oraz dogodne terminy. W lato wysokie rabaty;)")
    private String extraDescription;

    @ApiModelProperty(value = "Represents offer's extra level price", position = 10, example = "34.56")
    private Float extraPrice;

    @Valid
    @NotNull(message = "No user selected.")
    @ApiModelProperty(value = "Represents the user who submitted this offer", required = true, position = 11)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

}
