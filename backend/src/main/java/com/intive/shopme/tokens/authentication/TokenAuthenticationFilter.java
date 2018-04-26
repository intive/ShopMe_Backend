package com.intive.shopme.tokens.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final AuthenticationFailureHandler failureHandler;

    public TokenAuthenticationFilter(AuthenticationFailureHandler failureHandler, RequestMatcher requestMatcher) {
        super(requestMatcher);
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        final String header = request.getHeader(AUTHORIZATION_HEADER);

        if (header == null) {
            return getAuthenticationManager().authenticate(new JwtAuthenticationToken(null));
        }

        if (!header.startsWith(TOKEN_PREFIX)) {
            throw new JwtAuthenticationException("Incorrect header");
        }

        final String token = header.substring(TOKEN_PREFIX.length(), header.length());
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {

        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
