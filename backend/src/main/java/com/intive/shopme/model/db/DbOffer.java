package com.intive.shopme.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "OFFER")
@Data
@EqualsAndHashCode(callSuper = true)
public class DbOffer extends DbIdentifiable {

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String title;
    @ManyToOne
    private DbCategory category;
    private String baseDescription;
    private Double basePrice;
    private String extendedDescription;
    private Double extendedPrice;
    private String extraDescription;
    private Double extraPrice;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DbOwner owner;
}
