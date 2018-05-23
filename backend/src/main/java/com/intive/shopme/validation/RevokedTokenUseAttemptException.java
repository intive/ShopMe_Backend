package com.intive.shopme.validation;

import org.springframework.security.core.AuthenticationException;

public class RevokedTokenUseAttemptException extends AuthenticationException {

    public RevokedTokenUseAttemptException(String message) {
        super(message);
    }
}
