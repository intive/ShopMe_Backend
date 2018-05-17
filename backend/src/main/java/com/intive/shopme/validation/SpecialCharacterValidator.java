package com.intive.shopme.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.intive.shopme.config.AppConfig.SPECIAL_CHAR_SET;

public class SpecialCharacterValidator implements ConstraintValidator<SpecialCharacterCheck, String> {

    static final String MESSAGE = "Field cannot contain only special characters.";

    @Override
    public boolean isValid(String text, ConstraintValidatorContext context) {
        return !StringUtils.isWhitespace(text.replaceAll(SPECIAL_CHAR_SET,""));
    }
}
