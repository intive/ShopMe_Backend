package com.intive.shopme.config.security;

import com.intive.shopme.model.rest.Role;
import com.intive.shopme.model.rest.UserContext;
import com.intive.shopme.registration.RevokedTokenService;
import com.intive.shopme.validation.RevokedTokenUseAttemptException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtParser jwtParser;
    private final RevokedTokenService revokedTokenService;

    @Autowired
    public JwtAuthenticationProvider(JwtParser jwtParser, RevokedTokenService revokedTokenService) {
        this.jwtParser = jwtParser;
        this.revokedTokenService = revokedTokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String token = (String) authentication.getCredentials();
        if (token == null) {
            return JwtAuthenticationToken.anonymous();
        }

        final var claims = jwtParser.parse(token);
        final var grantedAuthorities = convertToGrantedAuthorities(claims);
        final var userContext = new UserContext(JwtParser.getUserId(claims), JwtParser.getEmail(claims), grantedAuthorities, JwtParser.getExpirationDate(claims));

        if (!revokedTokenService.isTokenRevoked(userContext)) {
            return new JwtAuthenticationToken(userContext, grantedAuthorities);
        }
        else
            throw new RevokedTokenUseAttemptException("Token has been revoked");
    }

    private Set<GrantedAuthority> convertToGrantedAuthorities(Claims claims) {
        Set<GrantedAuthority> set = new HashSet<>();
        for (Object scope : JwtParser.getScopes(claims)) {
            set.add(Role.valueOf("" + scope));
        }
        return set;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
