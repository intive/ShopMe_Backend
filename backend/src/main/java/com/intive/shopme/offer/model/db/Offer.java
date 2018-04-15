package com.intive.shopme.offer.model.db;

import com.intive.shopme.base.model.Identifiable;
import com.intive.shopme.category.model.db.Category;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Offer extends Identifiable {

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String title;

    @ManyToOne
    @JoinTable(name = "offer_category",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Category category;

    private String baseDescription;

    private Float basePrice;

    private String extendedDescription;

    private Float extendedPrice;

    private String extraDescription;

    private Float extraPrice;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Owner owner;
}
