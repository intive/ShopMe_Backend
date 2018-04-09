package com.intive.shopme.util.validation.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.UUID;

@ControllerAdvice
public class ErrorHandlingController {

    private final static Logger logger = LoggerFactory.getLogger(ErrorHandlingController.class);


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

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleRuntimeException(RuntimeException exception) {
        String errorLog = "An error occurred! Error log ID: " + UUID.randomUUID();
        logger.error(errorLog, exception);
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorLog);
    }
}

