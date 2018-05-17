package com.intive.shopme.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WhiteSpaceTabulatorValidator implements ConstraintValidator<WhiteSpaceTabulatorCheck, String> {

    static final String MESSAGE = "Field cannot contain only whitespace or tabulator.";

    @Override
    public boolean isValid(String text, ConstraintValidatorContext context) {
        return !StringUtils.isWhitespace(text);
    }
}
