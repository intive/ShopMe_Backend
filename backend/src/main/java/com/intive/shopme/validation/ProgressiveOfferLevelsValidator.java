package com.intive.shopme.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.intive.shopme.model.rest.Offer;
import org.apache.commons.lang3.StringUtils;

public class ProgressiveOfferLevelsValidator
        implements ConstraintValidator<ProgressiveOfferLevels, Offer> {

    @Override
    public void initialize(ProgressiveOfferLevels constraintAnnotation) {
    }

    @Override
    public boolean isValid(Offer offer, ConstraintValidatorContext context) {
        if (offer == null) {
            return true;
        }

        var extraIsPresent = StringUtils.isNotEmpty(offer.getExtraDescription()) ||
                offer.getExtraPrice() != null;
        var extendedIsComplete = StringUtils.isNotEmpty(offer.getExtendedDescription()) &&
                offer.getExtendedPrice() != null;

        return !(extraIsPresent && !extendedIsComplete);
    }
}
