package com.intive.shopme.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DbCategory extends DbIdentifiable {

    @Column(unique = true)
    private String name;
}
