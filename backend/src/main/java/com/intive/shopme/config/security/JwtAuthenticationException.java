package com.intive.shopme.config.security;

import org.springframework.security.core.AuthenticationException;

class JwtAuthenticationException extends AuthenticationException {

    JwtAuthenticationException(String msg) {
        super(msg);
    }
}
