package com.intive.shopme.registration;

import com.intive.shopme.model.rest.Address;
import com.intive.shopme.model.rest.Invoice;
import com.intive.shopme.model.rest.User;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ValidInvoiceIfInvoiceRequestedValidatorTest {

    private final ValidInvoiceIfInvoiceRequestedValidator validator = new ValidInvoiceIfInvoiceRequestedValidator();
    private Boolean isInvoiceRequest;

    @Test
    void invoiceRequest_is_false_should_be_valid() {
        final var user = new User();
        isInvoiceRequest = false;
        user.setInvoiceRequest(isInvoiceRequest);
        assertThat(validator.isValid(user)).isTrue();
    }

    @Test
    void invoiceRequest_is_null_user_not_filled_negative_validation() {
        final var user = new User();
        isInvoiceRequest = null;
        user.setInvoiceRequest(isInvoiceRequest);
        assertThat(validator.isValid(user)).isFalse();
    }

    @Test
    void invoiceRequest_is_null_user_filled_negative_validation() {
        final var user = fillRequiredFieldToValidator();
        isInvoiceRequest = null;
        user.setInvoiceRequest(isInvoiceRequest);
        assertThat(validator.isValid(user)).isFalse();
    }

    @Test
    void invoiceRequest_is_true_user_filled_positive_validation() {
        final var user = fillRequiredFieldToValidator();
        isInvoiceRequest = true;
        user.setInvoiceRequest(isInvoiceRequest);
        assertThat(validator.isValid(user)).isTrue();
    }

    @Test
    void invoiceRequest_is_true_user_not_filled_negative_validation() {
        final var user = new User();
        isInvoiceRequest = true;
        user.setInvoiceRequest(isInvoiceRequest);
        assertThat(validator.isValid(user)).isFalse();
    }

    private User fillRequiredFieldToValidator() {
        final var address = new Address();
        address.setId(UUID.randomUUID());
        setAddressIsFilled(address);

        final var invoice = new Invoice();
        invoice.setId(UUID.randomUUID());
        invoice.setInvoiceAddress(address);
        setHasCompanyDetailsIsFilled(invoice);

        final var user = new User();
        user.setId(UUID.randomUUID());
        user.setInvoice(invoice);
        user.setAddress(address);

        return user;
    }

    private void setHasCompanyDetailsIsFilled(Invoice invoice) {
        invoice.setCompanyName("test");
        invoice.setNip("test");
    }

    private void setAddressIsFilled(Address address) {
        address.setCity("test");
        address.setNumber("test");
        address.setStreet("test");
        address.setCity("test");
        address.setZipCode("test");
    }
}
