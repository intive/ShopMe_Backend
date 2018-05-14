package com.intive.shopme.config;

public final class ApiUrl {
    public static final String OFFERS = "/offers";
    public static final String CATEGORIES = "/categories";
    public static final String USERS = "/users";
    public static final String VOIVODESHIPS = "/voivodeships";
    public final static String LOGIN = USERS + "/login";
    public final static String CURRENT_USER = USERS + "/current";

    private ApiUrl() {
    }
}
