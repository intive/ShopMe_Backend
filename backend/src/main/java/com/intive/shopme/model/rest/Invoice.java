package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Invoice data", description = "Represents data for invoice")
public class Invoice extends Identifiable {

    @ApiModelProperty(value = "Represents user's company name", required = true, position = 2, example = "Januszex Sp.z.o.")
    private String companyName;

    @ApiModelProperty(value = "Represents user's company NIP number", required = true, position = 3, example = "123-456-78-90")
    private String nip;

    @ApiModelProperty(value = "Represents user's company address", required = true, position = 4)
    private Address invoiceAddress;

    @JsonIgnore
    public boolean hasCompanyDetails() {
        return !StringUtils.isEmpty(companyName) && !StringUtils.isEmpty(nip);
    }
}
