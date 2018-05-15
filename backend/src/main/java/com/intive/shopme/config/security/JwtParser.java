package com.intive.shopme.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
class JwtParser {

    private final MessageSource messageSource;
    private final String secret;


    @Autowired
    public JwtParser(MessageSource messageSource, @Value("${jwt.secret}") String secret) {
        this.secret = secret;
        this.messageSource = messageSource;
    }

    Claims parse(final String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(TextCodec.BASE64.decode(secret))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            String message = messageSource.getMessage("invalidToken",
                    null, LocaleContextHolder.getLocale());
            throw new JwtAuthenticationException(message);
        } catch (SignatureException e) {
            String message = messageSource.getMessage("invalidSignature",
                    null, LocaleContextHolder.getLocale());
            throw new JwtAuthenticationException(message);
        } catch (ExpiredJwtException e) {
            String message = messageSource.getMessage("tokenExpired",
                    null, LocaleContextHolder.getLocale());
            throw new JwtAuthenticationException(message);
        }
    }

    static UUID getUserId(final Claims claims) {
        return UUID.fromString(claims.getSubject());
    }

    static String getEmail(final Claims claims) {
        return claims.get("email", String.class);
    }

    static List getScopes(final Claims claims) {
        return claims.get("scopes", List.class);
    }
}
