package com.intive.shopme.category.model.db;

import com.intive.shopme.base.model.Identifiable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Category extends Identifiable {

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String translateKey;
}
