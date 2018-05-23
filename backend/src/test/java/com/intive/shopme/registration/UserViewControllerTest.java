package com.intive.shopme.registration;

import com.intive.shopme.model.db.DbInvoice;
import com.intive.shopme.model.db.DbUser;
import com.intive.shopme.model.rest.Invoice;
import com.intive.shopme.model.rest.UserWrite;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserViewControllerTest {

    private static final UUID ID = UUID.randomUUID();
    private static final String EMAIL = "foo";
    private static final String PASSWORD = "bar";
    private static final boolean INVOICE_REQUEST = true;
    private static final String PASSWORD_ENCODED = "baz";

    private final UserController controller = new UserController(null, null, null, PASSWORD_ENCODER, null);

    @Test
    void convertToView_should_map_basic_values_successfully() {
        final var user = new DbUser();
        user.setId(ID);
        user.setEmail(EMAIL);
        user.setInvoiceRequest(INVOICE_REQUEST);
        user.setInvoice(new DbInvoice());
        user.setPassword(PASSWORD_ENCODED);

        final var result = controller.convertToView(user);
        assertThat(result)
                .hasFieldOrPropertyWithValue("id", ID)
                .hasFieldOrPropertyWithValue("email", EMAIL)
                .hasFieldOrPropertyWithValue("password", null)
                .hasFieldOrProperty("invoice").isNotNull();
    }

    @Test
    void convertToDbModel_should_map_basic_values_successfully() {
        final var user = new UserWrite();
        user.setId(ID);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setInvoiceRequest(INVOICE_REQUEST);
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
