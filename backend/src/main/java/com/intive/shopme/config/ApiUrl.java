package com.intive.shopme.config;

import java.util.Collections;

public final class ApiUrl {
    public static final String OFFERS = "/offers";

    public static final String CATEGORIES = "/categories";

    public static final String USERS = "/users";
    public final static String USERS_LOGIN = USERS + "/login";
    public final static String USERS_LOGOUT = USERS + "/logout";
    public final static String USERS_CURRENT = USERS + "/current";

    public static final String VOIVODESHIPS = "/voivodeships";

    public static final AuthenticationRequestMatcher ALLOW_UNAUTHENTICATED_ACCESS = new AuthenticationRequestMatcher(
            Collections.singletonList(USERS_LOGIN)
    );

    private ApiUrl() {
    }
}
