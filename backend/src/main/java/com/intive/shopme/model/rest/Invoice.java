package com.intive.shopme.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@Data
@ApiModel(value = "Invoice data", description = "Represents data for invoice")
public class Invoice {

    @ApiModelProperty(value = "Represents unique id number", position = 1, example = "5d214c01-95c3-4ec4-8f68-51dfb80b191c")
    private UUID id;

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
