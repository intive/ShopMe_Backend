package com.intive.shopme.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ProgressiveOfferLevelsValidator.class })
@Documented
public @interface ProgressiveOfferLevels {
    String message() default "If EXTRA service level is offered you have to complete EXTENDED service level details as well!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
