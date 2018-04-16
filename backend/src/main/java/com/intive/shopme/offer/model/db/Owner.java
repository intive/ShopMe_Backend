package com.intive.shopme.offer.model.db;

import com.intive.shopme.base.model.Identifiable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Owner extends Identifiable {

    private String name;
    private String email;
    private String phoneNumber;
    private String additionalInfo;
}
