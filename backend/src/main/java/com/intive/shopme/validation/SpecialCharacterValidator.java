package com.intive.shopme.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SpecialCharacterValidator implements ConstraintValidator<SpecialCharacterCheck, String> {

    private final String SPECIAL_CHAR_SET = "[-/@#!*$%^&.'_+={}()]";

    @Override
    public boolean isValid(String text, ConstraintValidatorContext context) {
        return !text.replaceAll(SPECIAL_CHAR_SET,"").isEmpty();
    }
}
