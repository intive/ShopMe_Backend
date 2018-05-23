package com.intive.shopme.model.db;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class DbCategory {

    @Id
    private String name;

    @JsonCreator
    public DbCategory(String name) {
        this.name = name;
    }
}
