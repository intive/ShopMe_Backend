package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

import static com.intive.shopme.config.AppConfig.EMAIL_CLAIM_NAME;
import static com.intive.shopme.config.AppConfig.SCOPES_CLAIM_NAME;

@Service
class TokenService {

    private final PasswordEncoder passwordEncoder;
    private final long expirationTime;
    private final String secret;



    @Autowired
    TokenService(PasswordEncoder passwordEncoder,
                 @Value("${jwt.expiration-time}") long expirationTime,
                 @Value("${jwt.secret}") String secret) {
        this.expirationTime = expirationTime;
        this.passwordEncoder = passwordEncoder;
        this.secret = secret;
    }

    String exchangePasswordForToken(final DbUser user, final String password) {
        if (user == null || !passwordMatches(user, password)) {
            throw new BadCredentialsException("Incorrect email and/or password");
        }

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim(EMAIL_CLAIM_NAME, user.getEmail())
                .claim(SCOPES_CLAIM_NAME, user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(new Date(getExpirationDate()))
                .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.decode(secret))
                .compact();
    }

    long getExpirationDate() {
        return Instant.now().plusMillis(expirationTime).toEpochMilli();
    }

    private boolean passwordMatches(final DbUser user, final String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }


}
