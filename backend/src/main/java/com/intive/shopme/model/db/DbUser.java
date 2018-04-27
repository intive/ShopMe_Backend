package com.intive.shopme.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "USERS") // Note: USER is SQL reserved keyword
@Data
@EqualsAndHashCode(callSuper = true)
public class DbUser extends DbIdentifiable {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private String bankAccount;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DbAddress address;
    @ManyToOne
    private DbVoivodeship voivodeship;
    private Boolean invoiceRequest;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DbInvoice invoice;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "users_role",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();


    public void addRole(final Role role) {
        final RoleEntity roleEntity = RoleEntity.buildWithRole(role);
        roles.add(roleEntity);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles.stream()
                .map(RoleEntity::buildWithRole)
                .collect(Collectors.toSet());
    }

    public boolean hasRole(final Role role) {
        return roles.stream().anyMatch(
                roleEntity -> roleEntity.getRole().equals(role));
    }

    public Set<Role> getRoles() {
        Set<Role> userRoles = new HashSet<>();
        for (RoleEntity roleEntity : roles) {
            userRoles.add(roleEntity.getRole());
        }
        return userRoles;
    }
}
