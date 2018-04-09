package com.intive.shopme.util.validation.error;

import lombok.Data;

import java.util.HashMap;

@Data
public class ValidationExceptionResponse {

    private Integer code;
    private final HashMap<String, String> errors = new HashMap<>();

    public void add(String field, String msg) {
        errors.put(field, msg);
    }

    public HashMap getErrors() {
        return errors;
    }
}
