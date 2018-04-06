package com.intive.shopme.util.validation.validation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkInTextValidatorTest {

    static LinkInTextValidator linkInTextValidator;

    @BeforeAll
    static void initAll() {
        linkInTextValidator = new LinkInTextValidator();
    }

    @ParameterizedTest(name = "{index} => isValid({0})")
    @ValueSource(strings = { "https://www.example.com",
            "http://www.example.com",
            "www.example.com",
            "example.com",
            "http://blog.example.com",
            "http://www.example.com/product",
            "http://www.example.com/products?id=1&page=2",
            "http://www.example.com#up",
            "http://255.255.255.255",
            "255.255.255.255",
            "http://invalid.com/perl.cgi?key= | http://web-site.com/cgi-bin/perl.cgi?key1=value1&key2",
            "http://www.site.com:8008"
    })
    public void checkIsValid(String url) {
        boolean result = linkInTextValidator.isValid(url, null);
        assertTrue(result);
    }
}
