package com.intive.shopme.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Qba Walat
 * created on 06/03/2018
 */
public class User {

    @ApiModelProperty(value = "Represents user's name", required = true)
    private String name;
    @ApiModelProperty(value = "Represents user's email", required = true)
    private String email;
    @ApiModelProperty(value = "Represents user's phone number", required = true)
    private String phoneNumber;
    @ApiModelProperty(value = "Represents additional information typed by user")
    private String additionalInfo;

    public User(String name, String email, String phoneNumber, String additionalInfo) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.additionalInfo = additionalInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}