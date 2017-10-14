package com.sainsburys.services.plpsummary.response;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 *
 */
public class Product {

    /**
     *
     */
    private String title;

    /**
     *
     */
    @SerializedName("kcal_per_100g")
    private String kcalPer100g;

    /**
     *
     */
    @SerializedName("unit_price")
    private BigDecimal pricePerUnit;

    /**
     *
     */
    private String description;

    public Product(String title, String kcalPer100g, BigDecimal pricePerUnit, String description) {
        this.title = title;
        this.kcalPer100g = kcalPer100g;
        this.pricePerUnit = pricePerUnit;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getKcalPer100g() {
        return kcalPer100g;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Product{title=" + title +
                ", kcalPer100g=" + kcalPer100g +
                ", pricePerUnit=" + pricePerUnit +
                ", description=" + description +
                "}";
    }
}
