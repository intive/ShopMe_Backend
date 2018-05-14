package com.intive.shopme.token;

import com.intive.shopme.model.db.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class TokenService {
    private final long expirationTime;
    private final PasswordEncoder passwordEncoder;
    private final JwtFactory jwtFactory;

    @Autowired
    TokenService(@Value("${jwt.expiration-time}") long expirationTime,
                 PasswordEncoder passwordEncoder,
                 JwtFactory jwtFactory) {
        this.expirationTime = expirationTime;
        this.passwordEncoder = passwordEncoder;
        this.jwtFactory = jwtFactory;
    }

    boolean isUserAuthenticated(final DbUser user, final String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    String getToken(final DbUser user) {
        return jwtFactory.generateToken(user, expirationTime);
    }
}
