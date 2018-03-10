package com.intive.shopme.util.validation.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ExceptionResponse> handleInvalidInput(ConstraintViolationException ex) {

        Set violations = ex.getConstraintViolations();
        ConstraintViolation v = (ConstraintViolation) violations.toArray()[0];

        ExceptionResponse eR = new ExceptionResponse();
        eR.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        eR.setDescription("błąd danych wejściowych " + "w parametrze: " + v.getPropertyPath() + ". " + v.getMessage());

        return new ResponseEntity(eR, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}