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
@Constraint(validatedBy = {LinkInTextValidator.class})
public @interface LinkInTextCheck {

    String message() default "description can't contain any urls/links.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
