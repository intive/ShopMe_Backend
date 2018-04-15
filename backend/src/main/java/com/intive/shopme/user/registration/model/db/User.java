package com.intive.shopme.user.registration.model.db;

import com.intive.shopme.base.model.Identifiable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS") // Note: USER is SQL reserved keyword
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends Identifiable {

    private String name;

    private String surname;

    private String email;

    private String password;

    private String phoneNumber;

    private String bankAccount;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;

    private Boolean invoiceRequest;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Invoice invoice;

    // FIXME - changed to public to view-db access scope
    @Deprecated
    public User hidePassword() {
        User result = this;
        result.setPassword("");
        return result;
    }
}
