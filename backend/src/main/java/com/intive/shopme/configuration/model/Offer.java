package com.intive.shopme.configuration.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Qba Walat
 * created on 06/03/2018
 */

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "Represents offer's unique id number", required = true)
    private Long id;
    @ApiModelProperty(value = "Represents offer's date of submitting", required = true)
    private String date;
    @ApiModelProperty(value = "Represents offer's title - passed by user", required = true)
    private String title;
    @ApiModelProperty(value = "Represents offer's category", required = true)
    private Category category;
    @ApiModelProperty(value = "Represents offer's basic bundle", required = true)
    private Bundle basicBundle;
    @ApiModelProperty(value = "Represents offer's premium bundle")
    private Bundle premiumBundle;
    @ApiModelProperty(value = "Represents offer's premium plus bundle")
    private Bundle premiumPlusBundle;
    @ApiModelProperty(value = "Represents the user who submits an offer", required = true)
    private User user;

    public Offer(String date, String title, Category category, Bundle basicBundle, Bundle premiumBundle, Bundle premiumPlusBundle, User user) {
        this.date = date;
        this.title = title;
        this.category = category;
        this.basicBundle = basicBundle;
        this.premiumBundle = premiumBundle;
        this.premiumPlusBundle = premiumPlusBundle;
        this.user = user;
    }

    public enum Category {
        BUDOWA, OGROD
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Bundle getBasicBundle() {
        return basicBundle;
    }

    public void setBasicBundle(Bundle basicBundle) {
        this.basicBundle = basicBundle;
    }

    public Bundle getPremiumBundle() {
        return premiumBundle;
    }

    public void setPremiumBundle(Bundle premiumBundle) {
        this.premiumBundle = premiumBundle;
    }

    public Bundle getPremiumPlusBundle() {
        return premiumPlusBundle;
    }

    public void setPremiumPlusBundle(Bundle premiumPlusBundle) {
        this.premiumPlusBundle = premiumPlusBundle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}