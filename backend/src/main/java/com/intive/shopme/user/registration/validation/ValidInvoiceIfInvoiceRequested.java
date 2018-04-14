package com.intive.shopme.user.registration.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { ValidInvoiceIfInvoiceRequestedValidator.class })
public @interface ValidInvoiceIfInvoiceRequested {

    String message() default "Invoice requested but invoice data not filled in";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
