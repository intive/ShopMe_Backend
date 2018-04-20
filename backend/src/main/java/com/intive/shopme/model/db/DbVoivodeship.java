package com.intive.shopme.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "VOIVODESHIP")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DbVoivodeship extends DbIdentifiable {

    @Column(unique = true)
    @NotEmpty
    private String name;
}
