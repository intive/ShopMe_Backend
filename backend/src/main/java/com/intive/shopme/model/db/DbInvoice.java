package com.intive.shopme.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INVOICE")
@Data
@EqualsAndHashCode(callSuper = true)
public class DbInvoice extends DbIdentifiable {

    private String companyName;
    private String nip;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DbAddress invoiceAddress;
}
