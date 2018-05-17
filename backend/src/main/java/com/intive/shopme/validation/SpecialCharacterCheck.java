package com.intive.shopme.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SpecialCharacterValidator.class)
public @interface SpecialCharacterCheck {

    String message() default "Field cannot contain only special characters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
