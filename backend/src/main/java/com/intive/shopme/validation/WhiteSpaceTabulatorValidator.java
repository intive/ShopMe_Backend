package com.intive.shopme.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WhiteSpaceTabulatorValidator implements ConstraintValidator<WhiteSpaceTabulatorCheck, String> {

    @Override
    public boolean isValid(String text, ConstraintValidatorContext context) {
        return !text.replaceAll("\\s+","").isEmpty();
    }
}
