package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbUser;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TokenServiceTest {

    private static final UUID ID = UUID.randomUUID();
    private static final String CORRECT_PASSWORD = "foo";
    private final TokenService service = new TokenService(PASSWORD_ENCODER, 2000000, "secret");

    @Test
    void should_throw_badCredentialsException_when_password_is_invalid() {
        assertThrows(BadCredentialsException.class, () -> service.exchangePasswordForToken(createUser(), "invalidPassword"));
    }

    @Test
    void should_throw_badCredentialsException_when_user_is_null() {
        assertThrows(BadCredentialsException.class, () -> service.exchangePasswordForToken(null, CORRECT_PASSWORD));
    }

    @Test
    void should_return_token() {
        assertThat(service.exchangePasswordForToken(createUser(), CORRECT_PASSWORD)).isNotEmpty();
    }

    private static final PasswordEncoder PASSWORD_ENCODER = new PasswordEncoder() {

        @Override
        public String encode(CharSequence charSequence) {
            return "foo";
        }

        @Override
        public boolean matches(CharSequence charSequence, String s) {
            return s.contentEquals(charSequence);
        }
    };

    private DbUser createUser() {
        return DbUser.builder()
                .password(CORRECT_PASSWORD)
                .email("foo")
                .id(ID).build();
    }
}
