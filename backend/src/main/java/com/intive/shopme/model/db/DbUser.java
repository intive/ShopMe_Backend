package com.intive.shopme.model.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown = false, value = {"passwordHash", "password"})
@Entity
@Table(name = "USERS") // Note: USER is SQL reserved keyword
@Data
@EqualsAndHashCode(callSuper = true)
public class DbUser extends DbIdentifiable {

    private String name;
    private String surname;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private String bankAccount;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DbAddress address;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DbVoivodeship voivodeship;
    private Boolean invoiceRequest;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DbInvoice invoice;
}
