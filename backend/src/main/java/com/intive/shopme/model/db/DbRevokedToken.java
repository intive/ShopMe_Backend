package com.intive.shopme.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "REVOKED_TOKEN")
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class DbRevokedToken extends DbIdentifiable{

    private UUID userId;
    private Date expirationDate;

    DbRevokedToken() {
    }
}
