package com.intive.shopme.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    private DbUser user;

    @ManyToOne
    private DbVoivodeship voivodeship;

    private String city;

    public boolean isExtendedPresent() {
        return StringUtils.isNotEmpty(extendedDescription) ||
                extendedPrice != null;
    }

    public boolean isExtraPresent() {
        return StringUtils.isNotEmpty(extraDescription) ||
                extraPrice != null;
    }

    public boolean isExtendedComplete() {
        return StringUtils.isNotEmpty(extendedDescription) &&
                extendedPrice != null;
    }

    public boolean isExtraComplete() {
        return StringUtils.isNotEmpty(extraDescription) &&
                extraPrice != null;
    }
}
