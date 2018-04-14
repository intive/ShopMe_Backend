package com.intive.shopme.validator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public abstract class Validated<T> {

    private final static ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();

    protected void validate(final T model) {
        final Validator validator = VALIDATOR_FACTORY.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
