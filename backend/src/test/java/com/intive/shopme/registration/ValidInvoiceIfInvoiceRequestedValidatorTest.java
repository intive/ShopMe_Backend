package com.intive.shopme.registration;

import com.intive.shopme.model.rest.Address;
import com.intive.shopme.model.rest.Invoice;
import com.intive.shopme.model.rest.User;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ValidInvoiceIfInvoiceRequestedValidatorTest {

    private final ValidInvoiceIfInvoiceRequestedValidator validator = new ValidInvoiceIfInvoiceRequestedValidator();
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validatorAllFields = validatorFactory.getValidator();

    @Test
    void invoiceRequest_is_false_should_be_valid() {
        final var result = validator.isValid(createUser(false));
        assertThat(result).isTrue();
    }

    @Test
    void invoiceRequest_is_null_should_throw_violation() {
        final var user = createUser(null);
        Set<ConstraintViolation<User>> violations = validatorAllFields.validate(user);
        final var result = violations.isEmpty();
        assertThat(result).isFalse();
    }

    @Test
    void invoiceRequest_is_true_user_filled_positive_validation() {
        final var user = createUser(true);
        assertThat(validator.isValid(user)).isTrue();
    }

    @Test
    void invoiceRequest_is_true_user_not_filled_negative_validation() {
        final var user = new User();
        user.setInvoiceRequest(true);
        final var result = validator.isValid(user);
        assertThat(result).isFalse();
    }

    private User createUser(Boolean isInvoiceRequest) {
        final var address = new Address();
        address.setId(UUID.randomUUID());
        address.setCity("foo");
        address.setNumber("foo");
        address.setStreet("foo");
        address.setCity("foo");
        address.setZipCode("foo");

        final var invoice = new Invoice();
        invoice.setId(UUID.randomUUID());
        invoice.setInvoiceAddress(address);
        invoice.setCompanyName("foo");
        invoice.setNip("foo");

        final var user = new User();
        user.setId(UUID.randomUUID());
        user.setName("foo");
        user.setSurname("foo");
        user.setInvoice(invoice);
        user.setAddress(address);
        user.setPassword("foo");
        user.setInvoiceRequest(isInvoiceRequest);

        return user;
    }
}
