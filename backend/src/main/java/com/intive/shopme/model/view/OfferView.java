package com.intive.shopme.model.view;

import com.intive.shopme.model.db.Bundle;
import com.intive.shopme.model.db.Category;
import com.intive.shopme.model.db.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@ApiModel(value = "Offer", description = "Represents the offer created by user")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferView {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    @ApiModelProperty(value = "Represents offer's unique id number", required = true)
    private UUID id;

    @ApiModelProperty(value = "Represents offer's date of submitting", required = true)
    private Date date;

    @ApiModelProperty(value = "Represents offer's title - passed by user", required = true)
    private String title;

    @ApiModelProperty(value = "Represents offer's category", required = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Category category;

    @ApiModelProperty(value = "Represents offer's bundle", required = true)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Bundle bundle;

    @ApiModelProperty(value = "Represents the user who submits this offer", required = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

}
