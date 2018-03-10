package com.intive.shopme.util.validation.validation;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryValidator implements ConstraintValidator<Enum, String> {

    private Enum annotation;
    @Autowired

    @Override
    public void initialize(Enum annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String valueToValid, ConstraintValidatorContext constraintValidatorContext) {

        boolean result = false;

        Object[] enumValues = this.annotation.enumClass().getEnumConstants();

        if(enumValues != null) {
            for(Object en : enumValues) {
                if(valueToValid.equals(en.toString()) || (this.annotation.ignoreCase() && valueToValid.equalsIgnoreCase(en.toString()))) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}