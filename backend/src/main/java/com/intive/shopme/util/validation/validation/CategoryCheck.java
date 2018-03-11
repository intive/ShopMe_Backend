package com.intive.shopme.util.validation.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {CategoryValidator.class})
public @interface CategoryCheck {
    String message() default "invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
