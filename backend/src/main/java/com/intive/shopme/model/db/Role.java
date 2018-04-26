package com.intive.shopme.model.db;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    SA, USER, ANONYMOUS;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
