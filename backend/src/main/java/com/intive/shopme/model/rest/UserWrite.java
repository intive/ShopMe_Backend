package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intive.shopme.validation.SpecialCharacterCheck;
import com.intive.shopme.validation.WhiteSpaceTabulatorCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static com.intive.shopme.config.AppConfig.ACCEPTABLE_ONLY_DIGITS;
import static com.intive.shopme.config.AppConfig.AT_LEAST_ONE_DIGIT_OCCURS;
import static com.intive.shopme.config.AppConfig.AT_LEAST_ONE_UPPER_CASE_LETTER_OCCURS;
import static com.intive.shopme.config.AppConfig.BANK_ACCOUNT_LENGTH;
import static com.intive.shopme.config.AppConfig.DIGITS_NOT_ACCEPTABLE;
import static com.intive.shopme.config.AppConfig.PASSWORD_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.PASSWORD_MIN_LENGTH;
import static com.intive.shopme.config.AppConfig.USER_DESCRIPTION_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.USER_NAME_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.USER_NAME_MIN_LENGTH;
import static com.intive.shopme.config.AppConfig.USER_SURNAME_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.USER_SURNAME_MIN_LENGTH;

@Data
@ApiModel
public class UserWrite {

    @NotEmpty
    @Size(min = USER_NAME_MIN_LENGTH, message = "User's name contains not enough characters, minimum is " +
            USER_NAME_MIN_LENGTH + ".")
    @Size(max = USER_NAME_MAX_LENGTH, message = "User's name contains too much characters, maximum is " +
            USER_NAME_MAX_LENGTH + ".")
    @SpecialCharacterCheck
    @WhiteSpaceTabulatorCheck
    @Pattern(regexp = DIGITS_NOT_ACCEPTABLE, message = "User's name cannot contain any digits")
    @ApiModelProperty(value = "Represents user's name", required = true, position = 2, example = "Jan")
    private String name;

    @NotEmpty
    @Size(min = USER_SURNAME_MIN_LENGTH, message = "User's surname contains not enough characters, minimum is " +
            USER_SURNAME_MIN_LENGTH + ".")
    @Size(max = USER_SURNAME_MAX_LENGTH, message = "User's surname contains too much characters, maximum is " +
            USER_SURNAME_MAX_LENGTH + ".")
    @SpecialCharacterCheck
    @WhiteSpaceTabulatorCheck
    @Pattern(regexp = DIGITS_NOT_ACCEPTABLE, message = "User's surname cannot contain any digits")
    @ApiModelProperty(value = "Represents user's surname", required = true, position = 3, example = "Kowalski")
    private String surname;

    @NotEmpty
    @Email(message = "User's email has wrong format")
    @ApiModelProperty(value = "Represents user's email", required = true, position = 4, example = "unknown@gmail.com")
    private String email;

    @NotEmpty
    @Size(min = PASSWORD_MIN_LENGTH, message = "Password contains not enough characters, minimum is " +
            PASSWORD_MIN_LENGTH + ".")
    @Size(max = PASSWORD_MAX_LENGTH, message = "Password contains too much characters, maximum is " +
            PASSWORD_MAX_LENGTH + ".")
    @Pattern(regexp = AT_LEAST_ONE_DIGIT_OCCURS, message = "Password need contain at least one digit")
    @Pattern(regexp = AT_LEAST_ONE_UPPER_CASE_LETTER_OCCURS,
            message = "Password need contain at least one upper case letter")
    @ApiModelProperty(value = "Represents user's password", required = true, position = 5, example = "Password9")
    private String password;

    @NotEmpty
    @Pattern(regexp = "^[0]?[0-9]{9}$", message = "This phone number is invalid!")
    @ApiModelProperty(value = "Represents user's phone number", required = true, position = 6, example = "0234567890")
    private String phoneNumber;

    @NotEmpty
    @Size(min = BANK_ACCOUNT_LENGTH, max = BANK_ACCOUNT_LENGTH, message = "Bank account number should contain " +
            BANK_ACCOUNT_LENGTH + " digits.")
    @Pattern(regexp = ACCEPTABLE_ONLY_DIGITS, message = "The bank account number should contain only digits.")
    @ApiModelProperty(value = "Represents user's bank account number", required = true, position = 7,
            example = "01234567890123456789012345")
    private String bankAccount;

    @Valid
    @ApiModelProperty(value = "Represents user's address", required = true, position = 8)
    private Address address;

    @ApiModelProperty(value = "Represents user's voivodeship", required = true, position = 9)
    private Voivodeship voivodeship;

    @NotNull
    @ApiModelProperty(value = "Represents request user`s for invoice", required = true, position = 10, example = "true")
    private Boolean invoiceRequest;

    @Valid
    @ApiModelProperty(value = "Represents invoice data for user`s", position = 11)
    private Invoice invoice;

    @Size(max = USER_DESCRIPTION_MAX_LENGTH,
            message = "User's additional information has too many characters, maximum is " +
                    USER_DESCRIPTION_MAX_LENGTH + ".")
    @ApiModelProperty(value = "Represents additional information typed by user", position = 12,
            example = "Dodatkowe info")
    private String additionalInfo;

    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
}
