package com.intive.shopme.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "VOIVODESHIP")
@Data
@EqualsAndHashCode
@ToString
public class DbVoivodeship {

    @Id
    @Column(unique = true)
    @NotEmpty
    private String name;
}
