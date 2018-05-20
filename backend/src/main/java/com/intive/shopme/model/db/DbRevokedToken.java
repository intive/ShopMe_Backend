package com.intive.shopme.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "REVOKED_TOKEN")
@Data
@EqualsAndHashCode
public class DbRevokedToken {

    @Id
    private UUID userId;
    private Date expirationDate;

}
