package com.intive.shopme.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@ApiModel(value = "Offer's user", description = "Represents offer's owner.")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @ApiModelProperty(value = "Represents user's id number")
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ApiModelProperty(value = "Represents user's name", required = true)
    private String name;

    @ApiModelProperty(value = "Represents user's email", required = true)
    private String email;

    @ApiModelProperty(value = "Represents user's phone number", required = true)
    private String phoneNumber;

    @ApiModelProperty(value = "Represents additional information typed by user")
    private String additionalInfo;

}
