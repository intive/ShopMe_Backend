package com.intive.shopme.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Qba Walat
 * created on 06/03/2018
 */
public class Bundle {

    @ApiModelProperty(value = "Represents bundle's description")
    private String description;
    @ApiModelProperty(value = "Represents bundle's price")
    private String price;

    public Bundle(String description, String price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}