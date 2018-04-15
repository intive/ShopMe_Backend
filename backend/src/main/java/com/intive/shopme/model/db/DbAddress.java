package com.intive.shopme.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
@Data
@EqualsAndHashCode(callSuper = true)
public class DbAddress extends DbIdentifiable {

    private String street;
    private String number;
    private String city;
    private String zipCode;
}

