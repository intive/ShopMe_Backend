package com.intive.shopme.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;

public final class SwaggerApiInfoConfigurer {

    private static final String TITLE = "ShopMe by Intive Patronage `18 team";
    private static final String DESC = "ShopMe is a Web Application created during Intive Patronage `18 Project";
    private static final String VERSION = "1.7";

    private SwaggerApiInfoConfigurer() {
    }

    static ApiInfo createApiInfo() {
        return new ApiInfoBuilder().title(TITLE).description(DESC).version(VERSION).build();
    }

    public static final class Operations {

        public static final String NOT_FOUND = "Resource with ID was not found";
        public static final String CREATED = "New resource successfully created";
        public static final String TOKEN_GENERATED = "Successfully generated token";
        public static final String UPDATED = "Resource successfully updated";
        public static final String DELETED = "Resource successfully deleted";
        public static final String SUCCESS = "Successfully retrieved resource";
        public static final String LOGOUT = "Successfully logged out";
        public static final String VALIDATION_ERROR = "Data validation error";
        public static final String BAD_REQUEST = "Incorrect request parameters";
        public static final String UNAUTHORIZED = "Authentication is required";
        public static final String REVOKED = "Token that has been revoked";
        public static final String FORBIDDEN = "You are not authorized to do this";
        public static final String BAD_USER_PASS = "Incorrect email and/or password";

        private Operations() {
        }
    }
}
