package com.intive.shopme.tokens;

import com.intive.shopme.model.db.DbUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtFactory {

    private static final String EMAIL_CLAIM_NAME = "email";
    private static final String SCOPES_CLAIM_NAME = "scopes";

    private final String secret;

    public JwtFactory(@Value("${jwt.secret-base64}") String secret) {
        this.secret = secret;
    }

    public String generateToken(final DbUser user, final long expirationTime) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim(EMAIL_CLAIM_NAME, user.getEmail())
                .claim(SCOPES_CLAIM_NAME, user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(new Date(Instant.now().plus(expirationTime, ChronoUnit.MINUTES).toEpochMilli()))
                .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.decode(secret))
                .compact();
    }
}
