package com.intive.shopme.user.registration.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intive.shopme.base.model.Identifiable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Invoice extends Identifiable {

    private String companyName;

    private String nip;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address invoiceAddress;

    @JsonIgnore
    public boolean hasCompanyDetails() {
        return !StringUtils.isEmpty(companyName) && !StringUtils.isEmpty(nip);
    }
}
