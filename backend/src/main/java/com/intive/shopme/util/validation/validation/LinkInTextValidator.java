package com.intive.shopme.util.validation.validation;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LinkInTextValidator implements ConstraintValidator<LinkInTextCheck, String>{

    String pattern = "^((?!http://|https://).)*$";
    String pattern2 = "((pl|com|eu|de|uk|info|mail|biz|org|edu|net|pro|tk).)*$";

    @Override
    public void initialize(LinkInTextCheck constraintAnnotation) {
        String[] schemas = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemas);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(value);
        if(m.find()) {
            return true;
        } else {
            return false;
        }
    }
}
