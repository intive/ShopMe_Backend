package com.intive.shopme.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@ApiModel(value = "Offer", description = "Represents the offer created by user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @ApiModelProperty(value = "Represents offer's unique id number", position = 1,
            example = "33333333-3333-3333-3333-333333333333")
    private UUID id;

    @ApiModelProperty(value = "Represents offer's date of submitting", position = 2,
            example = "1234567890")
    private final Long date = System.currentTimeMillis();

    @ApiModelProperty(value = "Represents offer's title", required = true, position = 3,
            example = "Odśnieżanie Niebuszewo")
    private String title;

    @ApiModelProperty(value = "Represents offer's category", required = true, position = 4)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Category category;

    @ApiModelProperty(value = "Represents offer's base description", required = true, position = 5)
    private String baseDescription;

    @ApiModelProperty(value = "Represents offer's base price", required = true, position = 6)
    private float basePrice;

    @ApiModelProperty(value = "Represents offer's extended level description", position = 7)
    private String extendedDescription;

    @ApiModelProperty(value = "Represents offer's extended level price", position = 8)
    private float extendedPrice;

    @ApiModelProperty(value = "Represents offer's extra level description", position = 9)
    private String extraDescription;

    @ApiModelProperty(value = "Represents offer's extra level price", position = 10)
    private float extraPrice;

    @ApiModelProperty(value = "Represents the user who submitted this offer", required = true, position = 11)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

}
