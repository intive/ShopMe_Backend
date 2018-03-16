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
@ApiModel(value = "Offer's user", description = "Represents offer's owner")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @ApiModelProperty(value = "Represents user's id number", position = 1,
            example = "44444444-4444-4444-4444-444444444444")
    private UUID id;

    @ApiModelProperty(value = "Represents user's name", required = true, position = 2,
            example = "Jan Kowalski")
    private String name;

    @ApiModelProperty(value = "Represents user's email", required = true, position = 3,
            example = "unknown@gmail.com")
    private String email;

    @ApiModelProperty(value = "Represents user's phone number", required = true, position = 4,
            example = "+4801234567890")
    private String phoneNumber;

    @ApiModelProperty(value = "Represents additional information typed by user", position = 5,
            example = "Dodatkowe info")
    private String additionalInfo;

}
