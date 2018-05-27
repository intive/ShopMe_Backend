package com.intive.shopme.config.security;

import org.springframework.security.core.AuthenticationException;

class RevokedTokenUseAttemptException extends AuthenticationException {

    public RevokedTokenUseAttemptException(String message) {
        super(message);
    }
}
