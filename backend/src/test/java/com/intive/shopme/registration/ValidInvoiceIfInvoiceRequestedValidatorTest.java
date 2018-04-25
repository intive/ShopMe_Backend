package com.intive.shopme.registration;

import com.intive.shopme.model.rest.Address;
import com.intive.shopme.model.rest.Invoice;
import com.intive.shopme.model.rest.User;
import com.intive.shopme.model.rest.Voivodeship;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ValidInvoiceIfInvoiceRequestedValidatorTest {

    private static ValidInvoiceIfInvoiceRequestedValidator validator = new ValidInvoiceIfInvoiceRequestedValidator();

    @Test
    void isWithoutInvoiceRequestValid() {
        User user = createUser();
        user.setInvoiceRequest(false);
        assertThat(validator.isValid(user)).isTrue();
    }

    @Test
    void isNullInInvoiceRequestValid() {
        User user = createUser();
        user.setInvoiceRequest(null);
        assertThat(validator.isValid(user)).isTrue();
    }

    private User createUser() {
        User user = new User();
        user.setId(UUID.randomUUID());
        Address address = new Address();
        address.setId(UUID.randomUUID());
        user.setAddress(address);
        Voivodeship voivodeship = new Voivodeship();
        voivodeship.setId(UUID.randomUUID());
        voivodeship.setName("inne");
        Invoice invoice = new Invoice();
        invoice.setId(UUID.randomUUID());
        user.setInvoice(invoice);
        user.setVoivodeship(voivodeship);
        return user;
    }
}
