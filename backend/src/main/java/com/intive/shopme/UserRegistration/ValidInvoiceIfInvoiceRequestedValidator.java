package com.intive.shopme.UserRegistration;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ValidInvoiceIfInvoiceRequestedValidator implements
        ConstraintValidator<ValidInvoiceIfInvoiceRequested, Users> {

    @Override
    public void initialize(ValidInvoiceIfInvoiceRequested constraintAnnotation) {
    }

    @Override
    public boolean isValid(Users value, ConstraintValidatorContext context) {
        if (value.getInvoiceRequest()) {
            Invoice invoice = value.getInvoice();
            boolean result = (invoice != null) && invoice.hasCompanyDetails();

            Address invoiceAddress = invoice.getInvoiceAddress();
            result &= (invoiceAddress != null) && invoiceAddress.isFilled();
            return result;
        } else {
            return true;
        }
    }
}
