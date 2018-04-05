package com.intive.shopme.UserRegistration;

import com.intive.shopme.base.Identifiable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

}
