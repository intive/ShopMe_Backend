package com.intive.shopme.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {LinkInTextValidator.class})
public @interface LinkInTextCheck {

    String message() default "description can't contain any urls/links.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
