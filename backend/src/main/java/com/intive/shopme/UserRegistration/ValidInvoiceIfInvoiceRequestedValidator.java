package com.intive.shopme.UserRegistration;

import org.apache.commons.lang3.StringUtils;
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
            boolean result = (invoice != null) &&
                    !StringUtils.isEmpty(invoice.getCompanyName()) &&
                    !StringUtils.isEmpty(invoice.getNip());

            Address invoiceAddress = invoice.getInvoiceAddress();
            result &= (invoiceAddress != null) &&
                    !StringUtils.isEmpty(invoiceAddress.getStreet()) &&
                    !StringUtils.isEmpty(invoiceAddress.getNumber()) &&
                    !StringUtils.isEmpty(invoiceAddress.getCity()) &&
                    !StringUtils.isEmpty(invoiceAddress.getZipCode());
            return result;
        } else {
            value.setInvoice(null);
            return true;
        }
    }

}
