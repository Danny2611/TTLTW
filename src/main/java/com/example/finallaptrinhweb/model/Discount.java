
package com.example.finallaptrinhweb.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Discount {
    private int id;
    private String discountType;
    private BigDecimal discountValue;
    private Date expirationDate;

    public Discount() {
    }

    public Discount(int id, String discountType, BigDecimal discountValue, Date expirationDate) {
        this.id = id;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscountType() {
        return this.discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getDiscountValue() {
        return this.discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String toString() {
        return "Discount{id=" + this.id + ", discountType='" + this.discountType + "', discountValue=" + this.discountValue + ", expirationDate=" + this.expirationDate + "}";
    }
}
