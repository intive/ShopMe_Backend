package com.intive.shopme.registration;

import com.intive.shopme.model.rest.Address;
import com.intive.shopme.model.rest.Invoice;
import com.intive.shopme.model.rest.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
class ValidInvoiceIfInvoiceRequestedValidator implements Validator {

    boolean isValid(User value) {
        boolean result = true;
        if (value.getInvoiceRequest() != null && value.getInvoiceRequest()) {
            Invoice invoice = value.getInvoice();
            result = (invoice != null) && invoice.hasCompanyDetails();

            if (result) {
                Address invoiceAddress = invoice.getInvoiceAddress();
                result = (invoiceAddress != null) && invoiceAddress.isFilled();
            }
        }
        return result;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target != null) {
            if (!isValid((User) target)) {
                // FIXME be precise handle each case separately in isValid() method
                errors.rejectValue("invoice.companyName", "invoiceRequest.requires.companyName", "When invoice request, company name has to be filled");
                errors.rejectValue("invoice.nip", "invoiceRequest.requires.nip", "When invoice request, nip has to be filled");
                errors.rejectValue("address", "invoiceRequest.address", "When invoice request, address has to be filled");
            }
        }
    }
}
