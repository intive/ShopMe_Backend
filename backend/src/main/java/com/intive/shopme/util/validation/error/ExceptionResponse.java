package com.intive.shopme.util.validation.error;

import lombok.Setter;

import java.util.HashMap;

@Setter
public class ExceptionResponse {

    private int code;
    private HashMap<String, String> errors = new HashMap<>();

    public void add(String field, String msg) {
        errors.put(field, msg);
    }

    public HashMap getErrors() {
        return errors;
    }
}
