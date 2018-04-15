package com.intive.shopme.model.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.intive.shopme.config.AppConfig.USER_DESCRIPTION_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.USER_NAME_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.USER_NAME_MIN_LENGTH;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "Offer's user", description = "Represents offer's owner")
public class Owner extends Identifiable {

    @NotNull(message = "User's name cannot be empty.")
    @Length(min = USER_NAME_MIN_LENGTH, max = USER_NAME_MAX_LENGTH)
    @Pattern(regexp = "[a-zA-ZąĄćĆęĘłŁńŃóÓśŚżŻźŹ ]*", message = "Name has invalid characters.")
    @ApiModelProperty(value = "Represents user's name", required = true, position = 2, example = "Jan Kowalski")
    private String name;

    @Email(message = "User's email has wrong format")
    @ApiModelProperty(value = "Represents user's email", required = true, position = 3, example = "unknown@gmail.com")
    private String email;

    @Size(min = 9, max = 10, message = "The user's phone number has the wrong amount of digits.")
    @Pattern(regexp = "[0-9]+", message = "The user's phone number should contain only digits.")
    @ApiModelProperty(value = "Represents user's phone number", required = true, position = 4, example = "0234567890")
    private String phoneNumber;

    @ApiModelProperty(value = "Represents user's city", required = true, position = 5, example = "Szczecin")
    private String city;

    @ApiModelProperty(value = "Represents user's voivodeship", required = true, position = 6)
    private Voivodeship voivodeship;

    @Size(max = USER_DESCRIPTION_MAX_LENGTH, message = "The additional user's information has too many characters.")
    @ApiModelProperty(value = "Represents additional information typed by user", position = 7,
            example = "Dodatkowe info")
    private String additionalInfo;
}
