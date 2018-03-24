package com.intive.shopme.util.validation.validation;

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
@Constraint(validatedBy = {CategoryValidator.class})
public @interface CategoryCheck {
    String message() default "Invalid category id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
