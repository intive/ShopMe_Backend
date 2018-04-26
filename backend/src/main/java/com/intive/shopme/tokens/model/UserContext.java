package com.intive.shopme.tokens.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.UUID;

@Data
public class UserContext {

    private final UUID userId;
    private final String email;
    private final Set<GrantedAuthority> authorities;
}
