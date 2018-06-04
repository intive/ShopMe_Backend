package com.intive.shopme.model.rest;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, USER, ANONYMOUS;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
