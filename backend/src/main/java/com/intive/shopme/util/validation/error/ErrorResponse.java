package com.intive.shopme.util.validation.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
class ErrorResponse {

    private final HttpStatus httpStatus;
    private final String message;
}
