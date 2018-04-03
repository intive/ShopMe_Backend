package com.intive.shopme.util.validation.error;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Data
public class ErrorResponse  {
    private final HttpStatus httpStatus;
    private final String message;
}
