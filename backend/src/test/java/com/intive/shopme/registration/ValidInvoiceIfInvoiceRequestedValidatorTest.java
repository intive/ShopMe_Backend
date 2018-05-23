package com.intive.shopme.registration;

import com.intive.shopme.model.rest.Address;
import com.intive.shopme.model.rest.Invoice;
import com.intive.shopme.model.rest.UserWrite;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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
    void when_invoiceRequest_isNull_constraintViolations_is_expected() {
        final var user = createUser(null);
        final var result = validatorAllFields.validate(user).isEmpty();
        assertThat(result).isFalse();
    }

    @Test
    void invoiceRequest_is_true_user_filled_positive_validation() {
        final var user = createUser(true);
        assertThat(validator.isValid(user)).isTrue();
    }

    @Test
    void invoiceRequest_is_true_user_not_filled_negative_validation() {
        final var user = new UserWrite();
        user.setInvoiceRequest(true);
        final var result = validator.isValid(user);
        assertThat(result).isFalse();
    }

    private UserWrite createUser(Boolean isInvoiceRequest) {
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

        final var user = new UserWrite();
        user.setName("foo");
        user.setSurname("foo");
        user.setInvoice(invoice);
        user.setAddress(address);
        user.setPassword("foo");
        user.setInvoiceRequest(isInvoiceRequest);

        return user;
    }
}
