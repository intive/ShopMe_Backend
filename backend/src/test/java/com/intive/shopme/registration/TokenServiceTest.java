package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbUser;
import com.intive.shopme.model.rest.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TokenServiceTest {

    private static final DbUser user = new DbUser();
    private static final UUID ID = UUID.randomUUID();
    private static final String EMAIL = "typical@email.com";
    private static final String PASSWORD_ENCODED = "passwd";
    private final TokenService service = new TokenService(PASSWORD_ENCODER, 2000000, "secret");

    @BeforeAll
    static void init() {
        user.setId(ID);
        user.setEmail(EMAIL);
        user.addRole(Role.USER);
    }

    @Test
    void should_throw_badCredentialsException() {
        user.setPassword("goodPassword");
        assertThrows(BadCredentialsException.class, () -> {
            service.exchangePasswordForToken(user, "wrongPassword");
        });
    }

    @Test
    void should_return_token() {
        user.setPassword("goodPassword");
        var result = service.exchangePasswordForToken(user, "goodPassword");
        assertThat(result).isNotNull().isNotEmpty();
    }

    private static final PasswordEncoder PASSWORD_ENCODER = new PasswordEncoder() {

        @Override
        public String encode(CharSequence charSequence) {
            return PASSWORD_ENCODED;
        }

        @Override
        public boolean matches(CharSequence charSequence, String s) {
            return s.contentEquals(charSequence);
        }
    };
}
