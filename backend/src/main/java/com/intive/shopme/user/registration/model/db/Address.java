package com.intive.shopme.user.registration.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intive.shopme.base.model.Identifiable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Address extends Identifiable {

    private String street;

    private String number;

    private String city;

    private String zipCode;

    @JsonIgnore
    public boolean isFilled() {
        return !StringUtils.isEmpty(street) &&
                !StringUtils.isEmpty(number) &&
                !StringUtils.isEmpty(city) &&
                !StringUtils.isEmpty(zipCode);
    }
}

