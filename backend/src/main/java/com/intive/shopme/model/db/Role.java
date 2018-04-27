package com.intive.shopme.model.db;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, SA, ANONYMOUS;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
