package com.intive.shopme.user.registration;

import com.intive.shopme.model.Identifiable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "USERS") // Note: USER is SQL reserved keyword
@ApiModel(value = "Users", description = "Represents the user")
@Data
@EqualsAndHashCode(callSuper = true)
@ValidInvoiceIfInvoiceRequested
class User extends Identifiable {

    @ApiModelProperty(value = "Represents user's name",
            required = true, position = 2, example = "Jan")
    private String name;

    @ApiModelProperty(value = "Represents user's surname",
            required = true, position = 3, example = "Kowalski")
    private String surname;

    @ApiModelProperty(value = "Represents user's email",
            required = true, position = 4, example = "unknown@gmail.com")
    private String email;

    @ApiModelProperty(value = "Represents user's password",
            required = true, position = 5, example = "password")
    private String password;

    @ApiModelProperty(value = "Represents user's phone number",
            required = true, position = 6, example = "0234567890")
    private String phoneNumber;

    @ApiModelProperty(value = "Represents user's bank account number",
            required = true, position = 7, example = "01234567890123456789012345")
    private String bankAccount;

    @ApiModelProperty(value = "Represents user's address",
            required = true, position = 8)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;

    @ApiModelProperty(value = "Represents request user`s for invoice",
            required = true, position = 9, example = "true")
    private Boolean invoiceRequest;

    @ApiModelProperty(value = "Represents invoice data for user`s", position = 10)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Invoice invoice;

    // FIXME
    @Deprecated
    User hidePassword() {
        User result = this;
        result.setPassword("");
        return result;
    }
}
