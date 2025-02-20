package com.example.finallaptrinhweb.model;

import java.util.Date;

public class CouponCode {
    private int id;
    private String discountType;
    private double discountValue;
    private Date expirationDate;

    public CouponCode() {
    }

    public CouponCode(int id, String discountType, double discountValue, Date expirationDate) {
        this.id = id;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "CouponCode{" +
                "id=" + id +
                ", discountType='" + discountType + '\'' +
                ", discountValue=" + discountValue +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
