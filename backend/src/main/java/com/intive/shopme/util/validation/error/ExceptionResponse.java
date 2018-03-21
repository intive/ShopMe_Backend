package com.intive.shopme.util.validation.error;

import lombok.Setter;

import java.util.HashMap;

@Setter
public class ExceptionResponse {
    private int code;
    private HashMap errors = new HashMap<String, String>();

    public void add(String field, String msg) {
        errors.put(field, msg);
    }

    public HashMap getErrors() {
        return errors;
    }
}
