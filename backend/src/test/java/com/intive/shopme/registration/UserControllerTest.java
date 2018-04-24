package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbInvoice;
import com.intive.shopme.model.db.DbUser;
import com.intive.shopme.model.rest.Invoice;
import com.intive.shopme.model.rest.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserControllerTest {

    private static final UUID ID = UUID.randomUUID();
    private static final String EMAIL = "foo";
    private static final String PASSWORD = "bar";
    private static final String PASSWORD_ENCODED = "baz";

    private final UserController controller = new UserController(null, null, null, PASSWORD_ENCODER);

    @Test
    void convertToView_should_map_basic_values_successfully() {
        final var user = new DbUser();
        user.setId(ID);
        user.setEmail(EMAIL);
        user.setInvoice(new DbInvoice());

        final var result = controller.convertToView(user);
        assertThat(result)
                .hasFieldOrPropertyWithValue("id", ID)
                .hasFieldOrPropertyWithValue("email", EMAIL)
                .hasFieldOrProperty("invoice").isNotNull();
    }

    @Test
    void convertToDbModel_should_map_basic_values_successfully() {
        final var user = new User();
        user.setId(ID);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setInvoice(new Invoice());

        final var result = controller.convertToDbModel(user);
        assertThat(result)
                .hasFieldOrPropertyWithValue("id", ID)
                .hasFieldOrPropertyWithValue("email", EMAIL)
                .hasFieldOrPropertyWithValue("password", PASSWORD_ENCODED)
                .hasFieldOrProperty("invoice").isNotNull();

    }

    private static final PasswordEncoder PASSWORD_ENCODER = new PasswordEncoder() {

        @Override
        public String encode(CharSequence charSequence) {
            return PASSWORD_ENCODED;
        }

        @Override
        public boolean matches(CharSequence charSequence, String s) {
            return false;
        }
    };
}
