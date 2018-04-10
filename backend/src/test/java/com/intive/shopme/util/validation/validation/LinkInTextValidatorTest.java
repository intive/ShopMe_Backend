package com.intive.shopme.util.validation.validation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkInTextValidatorTest {

    private static LinkInTextValidator linkInTextValidator;

    @BeforeAll
    static void initAll() {
        linkInTextValidator = new LinkInTextValidator();
        linkInTextValidator.initialize(null);
    }

    @ParameterizedTest
    @MethodSource(value = "createListOfUrls")
    public void isUrlShouldBeInvalid(String url) {
        assertFalse(linkInTextValidator.isValid(url, null));
    }

    @ParameterizedTest
    @MethodSource(value = "createListOfUrlsInsideText")
    public void isUrlInsideTextShouldBeInvalid(String url) {
        assertFalse(linkInTextValidator.isValid(url, null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "www.google.pl Oferuje odsnieżnie powierzchni płaskich",
            "a.www.example.com.."
    })
    public void notFoundWWWInsideText(String url) {
        assertTrue(linkInTextValidator.isValid(url, null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "www.google.pl",
            "www.example.com",
    })
    public void notFoundWWW(String url) {
        assertTrue(linkInTextValidator.isValid(url, null));
    }

    private static Stream<String> createListOfUrlsInsideText() {
        return Stream.of(
                "http://123.234.34.56",
                "http://123.234.34.56/",
                "aahttps://www.example.comaa",
                "..http://www.example.com..",
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
                "https://www.example.com",
                "http://www.example.com",
                "http://blog.example.com",
                "http://www.example.com/product",
                "http://www.example.com/products?id=1&page=2",
                "http://www.example.com#up",
                "http://invalid.com/perl.cgi?key= | http://web-site.com/cgi-bin/perl.cgi?key1=value1&key2",
                "http://www.site.com:8008"
        );
    }
}
