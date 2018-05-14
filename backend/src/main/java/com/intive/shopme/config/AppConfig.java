package com.intive.shopme.config;

public final class AppConfig {

    public static final String ACCEPTABLE_TITLE_SEARCH_CHARS = "a-zA-Z0-9ąĄćĆęĘłŁńŃóÓśŚżŻźŹ ";
    public static final String SPECIAL_CHAR_SET = "[-/@#!*$%^&.'_+={}()]";
    public static final String ZIP_CODE_FORMAT = "^[0-9]{2}(?:-[0-9]{3})$";
    public static final String NIP_CODE_FORMAT = "^([0-9]{3})(?:-[0-9]{3})(?:-[0-9]{2})(?:-[0-9]{2})$";
    public static final String DIGITS_NOT_ACCEPTABLE = "[^0-9]+";
    public static final String ACCEPTABLE_ONLY_DIGITS = "[0-9]+";
    public static final String AT_LEAST_ONE_DIGIT_OCCURS = "^(?=.*[0-9]).*$";
    public static final String AT_LEAST_ONE_UPPER_CASE_LETTER_OCCURS = "^(?=.*[A-Z]).*$";

    public static final int USER_NAME_MIN_LENGTH = 3;
    public static final int USER_NAME_MAX_LENGTH = 20;

    public static final int USER_SURNAME_MIN_LENGTH = 2;
    public static final int USER_SURNAME_MAX_LENGTH = 50;

    public static final int OFFER_TITLE_MAX_LENGTH = 30;
    public static final int OFFER_DESCRIPTION_MAX_LENGTH = 500;
    public static final int USER_DESCRIPTION_MAX_LENGTH = 800;

    public static final int FIRST_PAGE = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int PAGE_SIZE_MAX = 100;

    public static final String DEFAULT_SORT_FIELD = "date";
    public static final String DEFAULT_SORT_DIRECTION = "DESC";

    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 30;

    public static final int PHONE_NUMBER_MIN_LENGTH = 9;
    public static final int PHONE_NUMBER_MAX_LENGTH = 10;

    public static final int BANK_ACCOUNT_LENGTH = 26;

    public static final int STREET_MIN_LENGTH = 3;
    public static final int CITY_MAX_LENGTH = 50;
    public static final int ZIP_CODE_MAX_LENGTH = 6;

    public static final String REST_ENTRY_POINT = "/**";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String EMAIL_CLAIM_NAME = "email";
    public static final String SCOPES_CLAIM_NAME = "scopes";

    private AppConfig() {
    }
}
