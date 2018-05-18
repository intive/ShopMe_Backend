package com.intive.shopme.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "EXPIRED_TOKENS")
@Data
@EqualsAndHashCode
public class DbExpiredToken {

    @Id
    private UUID userId;
    private Long expirationDate;

}
