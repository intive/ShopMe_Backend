package com.intive.shopme.util.validation.validation;

import org.apache.commons.validator.routines.UrlValidator;
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
    @ValueSource(strings = {"www.google.pl"})
    public void checkApacheValidator(String url) {
        UrlValidator urlValidator = new UrlValidator(LinkInTextValidator.schemas);
        assertFalse(urlValidator.isValid(url));
    }

    @ParameterizedTest
    @MethodSource(value = "createListOfUrls")
    public void rejectIsUrl(String url) {
        assertFalse(linkInTextValidator.isValid(url, null));
    }

    @ParameterizedTest
    @MethodSource(value = "createListOfUrlsInsideText")
    public void rejectIsUrlInsideText(String url) {
        assertFalse(linkInTextValidator.isValid(url, null));
    }

    private static Stream<String> createListOfUrlsInsideText() {
        return Stream.of(
                "www.google.pl Oferuje odsnieżnie powierzchni płaskich",
                "aahttps://www.example.comaa",
                "..http://www.example.com..",
                "a.www.example.com..",
                "ftp://example.com..",
                "@http://www.exam@ple.comaa",
                ".http://www.exa-mple.comaa",
                "http://www.exa-mple.com ",
                "Http://www.example.com ",
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
                "http://www.google.pl",
                "www.google.pl Oferuje odsnieżnie powierzchni płaskich",
                "www.google.pl",
                "https://www.example.com",
                "http://www.example.com",
                "www.example.com",
                "http://blog.example.com",
                "http://www.example.com/product",
                "http://www.example.com/products?id=1&page=2",
                "http://www.example.com#up",
                "http://invalid.com/perl.cgi?key= | http://web-site.com/cgi-bin/perl.cgi?key1=value1&key2",
                "http://www.site.com:8008"
        );
    }
}
