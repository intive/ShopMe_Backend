package com.intive.shopme.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.intive.shopme.config.AppConfig.SPECIAL_CHAR_SET;

public class SpecialCharacterValidator implements ConstraintValidator<SpecialCharacterCheck, String> {

    @Override
    public boolean isValid(String text, ConstraintValidatorContext context) {
        return !text.replaceAll(SPECIAL_CHAR_SET,"").isEmpty();
    }
}
