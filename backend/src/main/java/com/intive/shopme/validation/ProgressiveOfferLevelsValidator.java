package com.intive.shopme.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.intive.shopme.model.rest.Offer;

public class ProgressiveOfferLevelsValidator
        implements ConstraintValidator<ProgressiveOfferLevelsCheck, Offer> {

    static final String MESSAGE =
            "If EXTRA service level is offered you have to complete EXTENDED service level details as well!";

    @Override
    public boolean isValid(Offer offer, ConstraintValidatorContext context) {
        if (offer == null) {
            return true;
        }

        return !offer.isExtraPresent() || offer.isExtendedComplete();
    }
}
