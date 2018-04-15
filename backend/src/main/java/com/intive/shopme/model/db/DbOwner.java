package com.intive.shopme.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OWNER")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DbOwner extends DbIdentifiable {

    private String name;
    private String email;
    private String phoneNumber;
    private String additionalInfo;
    private String city;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DbVoivodeship voivodeship;
}
