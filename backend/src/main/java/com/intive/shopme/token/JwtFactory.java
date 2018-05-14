package com.intive.shopme.token;

import com.intive.shopme.model.db.DbUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

import static com.intive.shopme.config.AppConfig.EMAIL_CLAIM_NAME;
import static com.intive.shopme.config.AppConfig.SCOPES_CLAIM_NAME;

@Component
public class JwtFactory {

    private final String secret;

    public JwtFactory(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    public String generateToken(final DbUser user, final long expirationTime) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim(EMAIL_CLAIM_NAME, user.getEmail())
                .claim(SCOPES_CLAIM_NAME, user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(new Date(Instant.now().plusMillis(expirationTime).toEpochMilli()))
                .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.decode(secret))
                .compact();
    }
}
