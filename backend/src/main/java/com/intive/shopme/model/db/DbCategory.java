package com.intive.shopme.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
public class DbCategory {

    @Id
    @Column(unique = true)
    private String name;
}
