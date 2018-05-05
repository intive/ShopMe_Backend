package com.intive.shopme.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {WhiteSpaceTabulatorValidator.class})
public @interface WhiteSpaceTabulatorCheck {

    String message() default "Field cannot contain only whitespace or tabulator.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
