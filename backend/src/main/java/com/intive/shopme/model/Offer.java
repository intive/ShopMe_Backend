package com.intive.shopme.model;

import com.intive.shopme.base.Identifiable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

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

    @NotNull(message = "No title selected.")
    @Size(max = 30, message = "Too many characters. Maximum is 30.")
    @ApiModelProperty(value = "Represents offer's title", required = true, position = 3,
            example = "Odśnieżanie Niebuszewo")
    private String title;

    @Valid
    @NotNull(message = "No category selected.")
    @ApiModelProperty(value = "Represents offer's category", required = true, position = 4)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Category category;

    @NotNull(message = "No bundle selected.")
    @ApiModelProperty(value = "Represents offer's base description", required = true, position = 5,
            example = "Oferuję odśnieżanie powierzchni płaskich.")
    private String baseDescription;
    @Size(max = 500, message = "Too many characters. Maximum is 30.")
    @ApiModelProperty(value = "Represents offer's bundle", required = true)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Bundle bundle;

    @ApiModelProperty(value = "Represents offer's base price", required = true, position = 6, example = "10")
    private float basePrice;

    @ApiModelProperty(value = "Represents offer's extended level description", position = 7,
            example = "Oferuję odśnieżanie powierzchni płaskich. Profesjonalne narzędzia, " +
                    "wysoka jakość wykonania usługi oraz dogodne terminy.")
    private String extendedDescription;

    @ApiModelProperty(value = "Represents offer's extended level price", position = 8, example = "20")
    private float extendedPrice;

    @ApiModelProperty(value = "Represents offer's extra level description", position = 9,
            example = "Oferuję odśnieżanie powierzchni płaskich. Profesjonalne narzędzia, " +
                    "wysoka jakość wykonania usługi oraz dogodne terminy. W lato wysokie rabaty;)")
    private String extraDescription;

    @ApiModelProperty(value = "Represents offer's extra level price", position = 10, example = "30")
    private float extraPrice;

    @NotNull(message = "No user selected.")
    @ApiModelProperty(value = "Represents the user who submitted this offer", required = true, position = 11)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

}
