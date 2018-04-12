package com.intive.shopme.service;

import javax.validation.*;
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
