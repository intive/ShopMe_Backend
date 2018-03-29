package com.intive.shopme.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class LinkInTextValidator implements ConstraintValidator<LinkInTextCheck, String> {

    // taken from: https://mathiasbynens.be/demo/url-regex (@diegoperini)
    private static final String URL_RE =
            "(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)" +
                    "(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])" +
                    "(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|" +
                    "(?:(?:[a-z\\u00a1-\\uffff0-9]+-?)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]+-?)*" +
                    "[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))(?::\\d{2,5})?(?:/[^\\s]*)?";
    private static final String CUSTOM_WWW_RE = "www\\.(?:[a-z\\u00a1-\\uffff0-9]+)";

    private static final Pattern[] URL_DETECTORS = new Pattern[]{Pattern.compile(URL_RE, CASE_INSENSITIVE), Pattern.compile(CUSTOM_WWW_RE, CASE_INSENSITIVE)};

    @Override
    public boolean isValid(String text, ConstraintValidatorContext context) {
        return Stream.of(URL_DETECTORS)
                .noneMatch(pattern -> pattern.matcher(text).find());
    }
}
