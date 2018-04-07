package com.intive.shopme.util.validation.validation;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LinkInTextValidator implements ConstraintValidator<LinkInTextCheck, String>{

    private UrlValidator urlValidator;

    private static final String URL_REGEX = "((https?|ftp|file)://)?(www\\.)?[-a-zA-Z0-9+&@#/%?=~_|!:,.;]" +
            "*[-a-zA-Z0-9+&@#/%=~_|]\\.(pl|com|eu|de|uk|info|mail|biz|org|edu|net|pro|tk)";
    public static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);

    private String[] schemas = {UrlType.http.name(), UrlType.https.name(),
            UrlType.ftp.name(), UrlType.file.name()};

    @Override
    public void initialize(LinkInTextCheck constraintAnnotation) {
        urlValidator = new UrlValidator(schemas);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Matcher matcher = URL_PATTERN.matcher(value);
        if(checkMatcherResult(matcher)) {
            return urlValidator.isValid(prepareStringUrlToValidate(matcher));
        } else {
            return true;
        }
    }

    public Boolean checkMatcherResult(Matcher matcher) {
        return matcher.find();
    }

    private String prepareStringUrlToValidate(Matcher matcher) {
        return matcher.group().substring(0, 4).contains("www.") ?
                UrlType.http.name() + "://" + matcher.group() : matcher.group();
    }

    private enum UrlType {
        http, https, ftp, file
    }
}
