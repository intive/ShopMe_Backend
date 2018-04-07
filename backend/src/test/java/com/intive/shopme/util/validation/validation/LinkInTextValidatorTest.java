package com.intive.shopme.util.validation.validation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Matcher;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkInTextValidatorTest {

    static LinkInTextValidator linkInTextValidator;

    @BeforeAll
    static void initAll() {
        linkInTextValidator = new LinkInTextValidator();
        linkInTextValidator.initialize(null);
    }

    @ParameterizedTest
    @MethodSource(value = "createListOfUrlsInsideText")
    public void checkMatcherResult(String url) {
        Matcher matcher = linkInTextValidator.URL_PATTERN.matcher(url);
        assertTrue(linkInTextValidator.checkMatcherResult(matcher));
    }

    @ParameterizedTest
    @MethodSource(value = "createListOfUrls")
    public void checkIsUrlByApacheUrlValidator(String url) {
        assertTrue(linkInTextValidator.isValid(url, null));
    }

    @ParameterizedTest
    @ValueSource(strings = { "example.com" })
    public void checkIsNotUrlByApacheUrlValidator(String url) {
        assertFalse(linkInTextValidator.isValid(url, null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "aahttps://www.example.comaa",
            "..http://www.example.com.." })
    public void checkIsNotUrlInsideTextByApacheUrlValidator(String url) {
        assertFalse(linkInTextValidator.isValid(url, null));
    }

    private static Stream<String> createListOfUrlsInsideText() {
        return Stream.of(
                "aahttps://www.example.comaa",
                "..http://www.example.com..",
                "a.www.example.com..",
                "ftp://example.com..",
                "@http://www.exam@ple.comaa",
                ".http://www.exa-mple.comaa",
                "http://www.exa-mple.com ",
                "http://www.example.com\n",
                "_http://blog.example.com/",
                "_http://blog.example.com/dasda",
                " http://www.example.com/products?id=1&page=2",
                "http://www.example.com#up",
                "http://invalid.com/perl.cgi?key= | http://web-site.com/cgi-bin/perl.cgi?key1=value1&key2",
                "http://www.site.com:8008"
        );
    }

    private static Stream<String> createListOfUrls() {
        return Stream.of(
                "https://www.example.com",
                "http://www.example.com",
                "www.example.com",
                "http://blog.example.com",
                "http://www.example.com/product",
                "http://www.example.com/products?id=1&page=2",
                "http://www.example.com#up",
                "http://255.255.255.255",
                "255.255.255.255",
                "http://invalid.com/perl.cgi?key= | http://web-site.com/cgi-bin/perl.cgi?key1=value1&key2",
                "http://www.site.com:8008"
        );
    }
}
