package com.intive.shopme.model.db;

import com.intive.shopme.model.rest.Role;
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
public class DbRole implements Serializable {

    @Id
    @Enumerated(EnumType.STRING)
    private Role role;

    private DbRole() {
    }

    private DbRole(String role) {
        this.role = Role.valueOf(role);
    }

    public static DbRole buildWithRole(Role role) {
        DbRole dbRole = new DbRole();
        dbRole.role = role;
        return dbRole;
    }

    @Override
    public String toString() {
        return "DbRole{" +
                "role=" + role +
                '}';
    }
}
