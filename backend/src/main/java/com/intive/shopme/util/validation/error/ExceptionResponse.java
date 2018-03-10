package com.intive.shopme.util.validation.error;

import java.util.HashMap;

public class ExceptionResponse {
    private int code;
    private HashMap errors = new HashMap<String, String>();

    public void setCode(int code) {
        this.code = code;
    }

    public void add(String field, String msg) {
        errors.put(field, msg);
    }

    public HashMap getErrors() {
        return errors;
    }
}