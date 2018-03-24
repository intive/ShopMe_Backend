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
        ExceptionResponse eR = new ExceptionResponse();
        eR.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());

        for (int i = 0; i < violations.size(); i++) {
            ConstraintViolation v = (ConstraintViolation) violations.toArray()[i];
            eR.add(v.getPropertyPath().toString(), v.getMessage());
        }

        return new ResponseEntity<>(eR, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
