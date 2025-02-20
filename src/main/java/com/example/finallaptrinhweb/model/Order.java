
package com.example.finallaptrinhweb.model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private String username;
    private int userId;
    private int discountsId;
    private int shipId;
    private int quantity;
    private String status;
    private double totalAmount;
    private long phone;
    private String detailAddress;
    private boolean payment;
    private Timestamp dateCreated;
    private double totalPay;
    private double shipPrice;


    public Order() {

    }

    public Order(int id, String username, int userId, int discountsId, int shipId, int quantity, String status,
                 double totalAmount, long phone, String detailAddress, boolean payment, Timestamp dateCreated,
                 double totalPay, double shipPrice) {
        this.id = id;
        this.username = username;
        this.userId = userId;
        this.discountsId = discountsId;
        this.shipId = shipId;
        this.quantity = quantity;
        this.status = status;
        this.totalAmount = totalAmount;
        this.phone = phone;
        this.detailAddress = detailAddress;
        this.payment = payment;
        this.dateCreated = dateCreated;
        this.totalPay = totalPay;
        this.shipPrice = shipPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDiscountsId() {
        return discountsId;
    }

    public void setDiscountsId(int discountsId) {
        this.discountsId = discountsId;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public double getShipPrice() {
        return shipPrice;
    }

    public void setShipPrice(double shipPrice) {
        this.shipPrice = shipPrice;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                ", discountsId=" + discountsId +
                ", shipId=" + shipId +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", totalAmount=" + totalAmount +
                ", phone=" + phone +
                ", detailAddress='" + detailAddress + '\'' +
                ", payment=" + payment +
                ", dateCreated=" + dateCreated +
                ", totalPay=" + totalPay +
                ", shipPrice=" + shipPrice +
                '}';
    }
}

