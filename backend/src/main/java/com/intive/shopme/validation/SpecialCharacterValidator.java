package com.intive.shopme.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCharacterValidator implements ConstraintValidator<SpecialCharacterCheck, String> {

    private static String regex = "[-/@#!*$%^&.'_+={}()]";

    private static final Pattern detect = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String text, ConstraintValidatorContext context) {
        Matcher matcher =  detect.matcher(text);
        var count = 0;
        while(matcher.find()) {
            count ++;
        }

        return count < text.length();
    }
}
