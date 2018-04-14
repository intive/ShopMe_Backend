package com.intive.shopme.user.registration.validation;

import com.intive.shopme.user.registration.Address;
import com.intive.shopme.user.registration.Invoice;
import com.intive.shopme.user.registration.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidInvoiceIfInvoiceRequestedValidator implements ConstraintValidator<ValidInvoiceIfInvoiceRequested, User> {

    @Override
    public boolean isValid(User value, ConstraintValidatorContext context) {
        boolean result = true;
        if (value.getInvoiceRequest()) {
            Invoice invoice = value.getInvoice();
            result = (invoice != null) && invoice.hasCompanyDetails();

            if (result) {
                Address invoiceAddress = invoice.getInvoiceAddress();
                result = (invoiceAddress != null) && invoiceAddress.isFilled();
            }
        }
        return result;
    }
}
