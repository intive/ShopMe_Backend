package com.intive.shopme.token.authentication;

import com.intive.shopme.model.rest.UserContext;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final UserContext userContext;
    private String token;

    JwtAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {
        this(authorities, userContext, null, true);
        eraseCredentials();
    }

    JwtAuthenticationToken(String token) {
        this(null, null, token, false);
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
