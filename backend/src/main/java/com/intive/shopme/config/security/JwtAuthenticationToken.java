package com.intive.shopme.config.security;

import com.intive.shopme.model.rest.Role;
import com.intive.shopme.model.rest.UserContext;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final UserContext userContext;
    private String token;

    JwtAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {
        this(authorities, userContext, null, true);
        eraseCredentials();
    }

    JwtAuthenticationToken(String token) {
        this(null, null, token, false);
    }

    static Authentication anonymous() {
        return new JwtAuthenticationToken(null, Collections.<GrantedAuthority>singletonList(Role.ANONYMOUS));
    }

    private JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities,
                                   UserContext userContext, String token, boolean isAuthenticated) {
        super(authorities);
        this.userContext = userContext;
        this.token = token;
        super.setAuthenticated(isAuthenticated);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return userContext;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        token = null;
    }
}