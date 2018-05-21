package com.intive.shopme.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;

public class RevokedTokenUseAttemptException extends AuthenticationException {

    private static final Logger logger = LoggerFactory.getLogger(RevokedTokenUseAttemptException.class);

    public RevokedTokenUseAttemptException(String message) {
        super(message);
        logger.warn("Attempt of revoked token usage");
    }
}
