package com.intive.shopme.util.validation.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ValidationExceptionResponse> handleInvalidInput(ConstraintViolationException exception) {

        Set violations = exception.getConstraintViolations();
        ValidationExceptionResponse exceptionResponse = new ValidationExceptionResponse();
        exceptionResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());

        for (int i = 0; i < violations.size(); i++) {
            ConstraintViolation violation = (ConstraintViolation) violations.toArray()[i];
            exceptionResponse.add(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, exception.getName());
    }

    @ExceptionHandler(value = {AlreadyExistException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleAlreadyExistException(AlreadyExistException exception) {
        return new ErrorResponse(HttpStatus.CONFLICT, exception.getName());
    }
}
