package com.intive.shopme.configuration.api;

public final class AppConfiguration {

    public static final String ACCEPTABLE_TITLE_SEARCH_CHARS = "a-zA-Z0-9ąĄćĆęĘłŁńŃóÓśŚżŻźŹ ";

    public static final int USER_NAME_MIN_LENGTH = 3;
    public static final int USER_NAME_MAX_LENGTH = 20;

    public static final int OFFER_TITLE_MAX_LENGTH = 30;
    public static final int OFFER_DESCRIPTION_MAX_LENGTH = 500;
    public static final int USER_DESCRIPTION_MAX_LENGTH = 800;

    public static final String FIRST_PAGE = "1";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final int PAGE_SIZE_MAX = 100;

    public static final String DEFAULT_SORT_FIELD = "date";
    public static final String DEFAULT_SORT_DIRECTION = "DESC";

    private AppConfiguration() {
    }
}
