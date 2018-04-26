package com.intive.shopme.model.db;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "ROLE")
public class RoleEntity implements Serializable {

    @Id
    @Enumerated(EnumType.STRING)
    private Role role;

    private RoleEntity() {
    }

    private RoleEntity(String role) {
        this.role = Role.valueOf(role);
    }

    public static RoleEntity buildWithRole(Role role) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.role = role;
        return roleEntity;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "role=" + role +
                '}';
    }
}
