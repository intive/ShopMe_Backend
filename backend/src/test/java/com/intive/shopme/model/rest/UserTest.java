package com.intive.shopme.model.rest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static Validator validator = validatorFactory.getValidator();

    @ParameterizedTest
    @ValueSource(strings = {
            "foobarbazfoobarbazfoobarbaz",
            "fo",
            "       ", // white space
            "   ", // tabulator
            "@#!@",
            "123456789",
    })
    void not_correct_name_should_be_violations(String name) {
        final var user = createUser();
        user.setName(name);
        Set<ConstraintViolation<User>> validate = validator.validate(user);
        final var result = validate.isEmpty();
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "foobar",
            "foo@",
            "fo bar", // white space
            "fo     bar", // tabulator
    })
    void correct_name_violations_should_be_empty(String name) {
        final var user = createUser();
        user.setName(name);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        final var result = violations.isEmpty();
        assertThat(result).isTrue();
    }

   @ParameterizedTest
    @ValueSource(strings = {
            "foobarbazfoobarbazfoobarbazfoobarbazfoobarbazfoobar",
            "f",
            "       ", // white space
            "   ", // tabulator
            "@#!@",
            "123456789"
    })
    void not_correct_surname_should_be_violations(String surname) {
        final var user = createUser();
        user.setSurname(surname);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        final var result = violations.isEmpty();
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "foobar",
            "foo@",
            "fo bar", // white space
            "fo     bar", // tabulator
    })
    void correct_surname_violations_should_be_empty(String name) {
        final var user = createUser();
        user.setSurname(name);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        final var result = violations.isEmpty();
        assertThat(result).isTrue();
    }

    private User createUser() {
        final var user = new User();
        user.setName("foobar");
        user.setSurname("foobar");
        user.setPassword("foo");

        return user;
    }
}