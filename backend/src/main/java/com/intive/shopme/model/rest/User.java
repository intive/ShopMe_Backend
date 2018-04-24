package com.intive.shopme.model.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@ApiModel(value = "User", description = "Represents the user")
public class User {

    @ApiModelProperty(value = "Represents unique id number", position = 1, example = "5d214c01-95c3-4ec4-8f68-51dfb80b191c")
    private UUID id;

    @ApiModelProperty(value = "Represents user's name", required = true, position = 2, example = "Jan")
    private String name;

    @ApiModelProperty(value = "Represents user's surname", required = true, position = 3, example = "Kowalski")
    private String surname;

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

    @ApiModelProperty(value = "Represents request user`s for invoice", required = true, position = 10, example = "true")
    private Boolean invoiceRequest;

    @ApiModelProperty(value = "Represents invoice data for user`s", position = 11)
    private Invoice invoice;
}
