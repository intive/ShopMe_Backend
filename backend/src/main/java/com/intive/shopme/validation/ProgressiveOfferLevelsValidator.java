package com.intive.shopme.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.intive.shopme.model.rest.Offer;

public class ProgressiveOfferLevelsValidator
        implements ConstraintValidator<ProgressiveOfferLevelsCheck, Offer> {

    @Override
    public void initialize(ProgressiveOfferLevelsCheck constraintAnnotation) {
    }

    @Override
    public boolean isValid(Offer offer, ConstraintValidatorContext context) {
        if (offer == null) {
            return true;
        }

        return !(offer.isExtraPresent() && !offer.isExtendedComplete());
    }
}
