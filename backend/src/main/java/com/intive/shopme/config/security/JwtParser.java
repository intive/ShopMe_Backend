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
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
class JwtParser {

    private static final String INVALID_TOKEN = "Provided JWT token is invalid.";
    private static final String INVALID_TOKEN_SIGNATURE = "Provided JWT token has invalid signature.";
    private static final String TOKEN_EXPIRED = "Provided JWT token already expired.";

    private final String secret;

    @Autowired
    public JwtParser(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    Claims parse(final String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(TextCodec.BASE64.decode(secret))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException(INVALID_TOKEN);
        } catch (SignatureException e) {
            throw new JwtAuthenticationException(INVALID_TOKEN_SIGNATURE);
        } catch (ExpiredJwtException e) {
            throw new JwtAuthenticationException(TOKEN_EXPIRED);
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
