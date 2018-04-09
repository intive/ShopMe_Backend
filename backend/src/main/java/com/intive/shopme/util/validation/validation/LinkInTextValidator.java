package com.intive.shopme.util.validation.validation;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
public class LinkInTextValidator implements ConstraintValidator<LinkInTextCheck, String> {

    private static final String[] SCHEMES = Stream.of(UrlType.values()).map(UrlType::name).toArray(String[]::new);
    private static final String URL_REGEX =
            "((((https?|ftp|file)://)|(www\\.))|(((https?)://)(www\\.)?))" +
                    "[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]" +
                    "\\.(pl|com|eu|de|uk|info|mail|biz|org|edu|net|pro|tk)";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);
    private UrlValidator urlValidator;

    @Override
    public void initialize(LinkInTextCheck constraintAnnotation) {
        urlValidator = new UrlValidator(SCHEMES);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Matcher matcher = URL_PATTERN.matcher(value.toLowerCase());
        if (checkMatcherResult(matcher)) {
            return !checkIsUrlByValidator(matcher);
        } else {
            return true;
        }
    }

    public boolean checkIsUrlByValidator(Matcher matcher) {
        return urlValidator.isValid(prepareStringUrlToValidate(matcher));
    }

    public boolean checkMatcherResult(Matcher matcher) {
        return matcher.find();
    }

    private String prepareStringUrlToValidate(Matcher matcher) {
        return matcher.group().substring(0, 4).contains("www.") ?
                UrlType.HTTP.name() + "://" + matcher.group() : matcher.group();
    }

    private enum UrlType {
        HTTP, HTTPS, FTP, FILE
    }
}
