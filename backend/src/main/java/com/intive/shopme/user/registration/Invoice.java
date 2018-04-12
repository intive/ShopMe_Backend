package com.intive.shopme.user.registration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intive.shopme.model.Identifiable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@ApiModel(value = "Invoice data", description = "Represents data for invoice")
@Data
@EqualsAndHashCode(callSuper = true)
class Invoice extends Identifiable {

    @ApiModelProperty(value = "Represents user's company name",
            required = true, position = 2, example = "Januszex Sp.z.o.")
    private String companyName;

    @ApiModelProperty(value = "Represents user's company NIP number",
            required = true, position = 3, example = "123-456-78-90")
    private String nip;

    @ApiModelProperty(value = "Represents user's company address",
            required = true, position = 4)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address invoiceAddress;

    @JsonIgnore
    boolean hasCompanyDetails() {
        return !StringUtils.isEmpty(companyName) && !StringUtils.isEmpty(nip);
    }
}
