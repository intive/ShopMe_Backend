package com.intive.shopme.model.rest;

import com.intive.shopme.validation.SpecialCharacterCheck;
import com.intive.shopme.validation.WhiteSpaceTabulatorCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

import static com.intive.shopme.config.AppConfig.DIGITS_NOT_ACCEPTABLE;
import static com.intive.shopme.config.AppConfig.USER_NAME_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.USER_NAME_MIN_LENGTH;
import static com.intive.shopme.config.AppConfig.USER_SURNAME_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.USER_SURNAME_MIN_LENGTH;

@Data
@ApiModel(value = "User", description = "Represents the user")
public class User {

    @ApiModelProperty(value = "Represents unique id number", position = 1, example = "5d214c01-95c3-4ec4-8f68-51dfb80b191c")
    private UUID id;

    @NotEmpty
    @Size(min = USER_NAME_MIN_LENGTH, message = "User's name contains not enough characters, minimum is " + USER_NAME_MIN_LENGTH + ".")
    @Size(max = USER_NAME_MAX_LENGTH, message = "User's name contains too much characters, maximum is " + USER_NAME_MAX_LENGTH + ".")
    @SpecialCharacterCheck
    @WhiteSpaceTabulatorCheck
    @Pattern(regexp = DIGITS_NOT_ACCEPTABLE)
    @ApiModelProperty(value = "Represents user's name", required = true, position = 2, example = "Jan")
    private String name;

    @NotEmpty
    @Size(min = USER_SURNAME_MIN_LENGTH, message = "User's surname contains not enough characters, minimum is " + USER_SURNAME_MIN_LENGTH + ".")
    @Size(max = USER_SURNAME_MAX_LENGTH, message = "User's surname contains too much characters, maximum is " + USER_SURNAME_MAX_LENGTH + ".")
    @SpecialCharacterCheck
    @WhiteSpaceTabulatorCheck
    @Pattern(regexp = DIGITS_NOT_ACCEPTABLE)
    @ApiModelProperty(value = "Represents user's surname", required = true, position = 3, example = "Kowalski")
    private String surname;

    @Email(message = "User's email has wrong format")
    @ApiModelProperty(value = "Represents user's email", required = true, position = 4, example = "unknown@gmail.com")
    private String email;

    @NotEmpty
    @ApiModelProperty(value = "Represents user's password", required = true, position = 5, example = "password")
    private String password;

    @ApiModelProperty(value = "Represents user's phone number", required = true, position = 6, example = "0234567890")
    private String phoneNumber;

    @ApiModelProperty(value = "Represents user's bank account number", required = true, position = 7, example = "01234567890123456789012345")
    private String bankAccount;

    @ApiModelProperty(value = "Represents user's address", required = true, position = 8)
    private Address address;

    @ApiModelProperty(value = "Represents user's voivodeship", required = true, position = 9)
    private Voivodeship voivodeship;

    @NotNull
    @ApiModelProperty(value = "Represents request user`s for invoice", required = true, position = 10, example = "true")
    private Boolean invoiceRequest;

    @ApiModelProperty(value = "Represents invoice data for user`s", position = 11)
    private Invoice invoice;
}
