package com.intive.shopme.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintValidator;

import static org.assertj.core.api.Assertions.assertThat;

class LinkInTextValidatorTest {

    private static ConstraintValidator<?, String> validator = new LinkInTextValidator();

    @ParameterizedTest
    @ValueSource(strings = {
            "test",
            "1.1.1.1",
            "a.b.c.d",
            "test .dot .com",
            "httx://hidden.url.com",
            "https:xxa.pl zmień xx na //",
            "www. dalej.jest.zielono",
            "http://s.234.34.56",
            "http://127.0.0.1",
            "http://127.127.0.0",
            "ftp://0.127.0.0",
    })
    void not_url_should_be_valid(String url) {
        assertThat(validator.isValid(url, null)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "www.google.pl Oferuje odsnieżanie powierzchni płaskich",
            "a.www.example.com..",
            "http://www.google.pl",
            "https://www.example.com",
            "http://www.example.com",
            "http://blog.example.com",
            "http://www.example.com/product",
            "http://www.example.com/products?id=1&page=2",
            "http://www.example.com#up",
            "http://invalid.com/perl.cgi?key= | http://web-site.com/cgi-bin/perl.cgi?key1=value1&key2",
            "http://www.site.com:8008"
    })
    void normalUrls_should_be_invalid(String url) {
        assertThat(validator.isValid(url, null)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "www.google.pl",
            "www.example.com",
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
    })
    void nestedUrls_should_be_invalid(String url) {
        assertThat(validator.isValid(url, null)).isFalse();
    }

    @Test
    void nullIsValid() {
        assertThat(validator.isValid(null, null)).isTrue();
    }
}
