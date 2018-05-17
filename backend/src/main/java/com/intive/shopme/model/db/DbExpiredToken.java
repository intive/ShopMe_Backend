package com.intive.shopme.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
@Table(name = "EXPIRED_TOKENS")
@Data
@EqualsAndHashCode
public class DbExpiredToken {

    @Id
    @NotEmpty
    private UUID userId;

    @NotEmpty
    private Long expirationDate;

}
