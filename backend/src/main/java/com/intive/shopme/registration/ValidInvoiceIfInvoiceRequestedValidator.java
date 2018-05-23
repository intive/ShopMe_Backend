package com.intive.shopme.registration;

import com.intive.shopme.model.rest.UserWrite;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
class ValidInvoiceIfInvoiceRequestedValidator implements Validator {

    boolean isValid(UserWrite value) {
        boolean result = true;
        if (value.getInvoiceRequest()) {
            final var invoice = value.getInvoice();
            result = (invoice != null) && invoice.hasCompanyDetails();

            if (result) {
                final var invoiceAddress = invoice.getInvoiceAddress();
                result = (invoiceAddress != null) && invoiceAddress.isFilled();
            }
        }
        return result;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserWrite.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target != null) {
            if (!isValid((UserWrite) target)) {
                // FIXME be precise handle each case separately in isValid() method
                errors.rejectValue("invoice.companyName", "invoiceRequest.requires.companyName",
                        "When invoice request, company name has to be filled");
                errors.rejectValue("invoice.nip", "invoiceRequest.requires.nip",
                        "When invoice request, nip has to be filled");
                errors.rejectValue("address", "invoiceRequest.address",
                        "When invoice request, address has to be filled");
            }
        }
    }
}
