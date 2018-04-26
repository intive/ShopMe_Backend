package com.intive.shopme.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS") // Note: USER is SQL reserved keyword
@Data
@EqualsAndHashCode(callSuper = true)
public class DbUser extends DbIdentifiable {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private String bankAccount;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DbAddress address;
    @ManyToOne
    private DbVoivodeship voivodeship;
    private Boolean invoiceRequest;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DbInvoice invoice;
}
