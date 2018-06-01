package com.intive.shopme.model.rest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;

// TODO remove duplication of user object building - use @MethodSource?
// TODO add test for valid/invalid cases of properties: email, password, bank account, phone number
class UserWriteTest {

    private static final String VALID_NAME = "OneWord";
    private static final String VALID_EMAIL = "foo@bar.baz";
    private static final String VALID_SURNAME = "OneWord";
    private static final String VALID_PASSWORD = "ValidPassword123!";
    private static final String VALID_BANK_ACCOUNT = "12345678901234567890123456";
    private static final String VALID_PHONE_NUMBER = "603991077";

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @ParameterizedTest
    @ValueSource(strings = {
            "foobarbazfoobarbazfoobarbaz",
            "fo",
            "       ",
            "\t",
            "@#!@",
            "123456789",
            "foo9"
    })
    void invalid_name_should_validate_with_violations(String name) {
        final var user = createValidUserWithName(name);

        assertThat(validator.validate(user)).isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "foobar",
            "foo@",
            "fo bar",
            "fo\tbar"
    })
    void valid_name_should_validate_without_violations(String name) {
        final var user = createValidUserWithName(name);

        assertThat(validator.validate(user)).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "foobarbazfoobarbazfoobarbazfoobarbazfoobarbazfoobar",
            "f",
            "       ",
            "\t",
            "@#!@",
            "123456789",
            "foo9"
    })
    void invalid_surname_should_validate_with_violations(String surname) {
        final var user = createValidUserWithSurname(surname);

        assertThat(validator.validate(user)).isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "foobar",
            "foo@",
            "fo bar",
            "fo\tbar"
    })
    void valid_surname_should_validate_without_violations(String surname) {
        final var user = createValidUserWithSurname(surname);

        assertThat(validator.validate(user)).isEmpty();
    }

    private static UserWrite createValidUserWithName(String name) {
        return createUser(VALID_SURNAME, name);
    }

    private static UserWrite createValidUserWithSurname(String surname) {
        return createUser(surname, VALID_NAME);
    }

    private static UserWrite createUser(String surname, String name) {
        final var user = new UserWrite();
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(VALID_PASSWORD);
        user.setPhoneNumber(VALID_PHONE_NUMBER);
        user.setBankAccount(VALID_BANK_ACCOUNT);
        user.setEmail(VALID_EMAIL);
        user.setInvoiceRequest(Boolean.TRUE);
        return user;
    }
}
