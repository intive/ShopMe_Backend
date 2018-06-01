package com.intive.shopme.config;

import com.intive.shopme.config.security.RevokedTokenUseAttemptException;
import com.intive.shopme.validation.AlreadyExistException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static com.intive.shopme.config.AppConfig.CONSTRAINTS_JSON_KEY;
import static com.intive.shopme.config.AppConfig.ERROR_ID_JSON_KEY;
import static com.intive.shopme.config.AppConfig.VALIDATION_DESCRIPTION_JSON_KEY;
import static java.util.stream.Collectors.toMap;

@Log4j2
@ControllerAdvice
public class ErrorHandlingConfig {

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException exception) {
        return ResponseEntity
                .unprocessableEntity()
                .body(Map.of(CONSTRAINTS_JSON_KEY, prepareConstraintsDescriptions(exception.getConstraintViolations())));
    }

    private static Map<String, String> prepareConstraintsDescriptions(Set<ConstraintViolation<?>> violations) {
        return violations
                .stream()
                .collect(toMap(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage));
    }

    @ExceptionHandler(value = DataRetrievalFailureException.class)
    @ResponseBody
    public ResponseEntity handleDataRetrievalFailureException(DataRetrievalFailureException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(createResponseBody(exception.getMessage()));
    }

    @ExceptionHandler(value = AlreadyExistException.class)
    @ResponseBody
    public ResponseEntity handleAlreadyExistException(AlreadyExistException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(createResponseBody(exception.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(createResponseBody(exception.getRootCause().getMessage()));
    }

    @ExceptionHandler(value = {AccessDeniedException.class, BadCredentialsException.class, RevokedTokenUseAttemptException.class})
    @ResponseBody
    public ResponseEntity handleAuthenticationException(RuntimeException exception, HttpServletRequest request) {
        log.warn("{} URL: {}", exception.getMessage(), request.getRequestURL());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(createResponseBody(exception.getMessage()));
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseEntity handleRuntimeException(RuntimeException exception) {
        final var id = UUID.randomUUID();
        log.error("ID: {}", id, exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        ERROR_ID_JSON_KEY, id,
                        VALIDATION_DESCRIPTION_JSON_KEY, exception.getMessage()));
    }

    private static Map<String, String> createResponseBody(String message) {
        return Map.of(VALIDATION_DESCRIPTION_JSON_KEY, message);
    }
}
