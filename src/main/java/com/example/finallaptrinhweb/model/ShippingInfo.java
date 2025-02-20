package com.example.finallaptrinhweb.model;

import java.math.BigDecimal;
import java.sql.Date;

public class ShippingInfo {
    private int id;
    private BigDecimal shipPrice;
    private Date dateCreated;

    public ShippingInfo() {
    }

    public ShippingInfo(int id, BigDecimal shipPrice, Date dateCreated) {
        this.id = id;
        this.shipPrice = shipPrice;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getShipPrice() {
        return this.shipPrice;
    }

    public void setShipPrice(BigDecimal shipPrice) {
        this.shipPrice = shipPrice;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "ShippingInfo{id=" + this.id + ", shipPrice=" + this.shipPrice + ", dateCreated=" + this.dateCreated + "}";
    }
}