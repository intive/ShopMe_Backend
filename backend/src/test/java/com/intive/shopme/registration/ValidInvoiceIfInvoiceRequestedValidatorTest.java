package com.intive.shopme.registration;

import com.intive.shopme.common.Validated;
import com.intive.shopme.model.rest.Address;
import com.intive.shopme.model.rest.Invoice;
import com.intive.shopme.model.rest.User;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidInvoiceIfInvoiceRequestedValidatorTest extends Validated<User> {

    private final ValidInvoiceIfInvoiceRequestedValidator validator = new ValidInvoiceIfInvoiceRequestedValidator();

    @Test
    void invoiceRequest_is_false_should_be_valid() {
        final var result = validator.isValid(createUser(false));
        assertThat(result).isTrue();
    }

    @Test
    void invoiceRequest_is_null_expected_exception() {
        final var user = createUser(null);
        assertThrows(ConstraintViolationException.class, () -> validate(user));
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
        user.setInvoice(invoice);
        user.setAddress(address);
        user.setPassword("foo");
        user.setInvoiceRequest(isInvoiceRequest);

        return user;
    }
}
