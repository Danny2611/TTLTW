package com.example.finallaptrinhweb.model;

public class OrderProduct {
    private int id;
    private int orderId;
    private int discountsId;
    private String productName;
    private String imageUrl;
    private int quantity;
    private double price;
    private double total;
    private int productId;

    public OrderProduct() {
        // Default constructor
    }

    public OrderProduct(int id, int orderId, int discountsId, String productName,
                        String imageUrl, int quantity, double price, double total) {
        this.id = id;
        this.orderId = orderId;
        this.discountsId = discountsId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public int getDiscountsId() {
        return discountsId;
    }

    public void setDiscountsId(int discountsId) {
        this.discountsId = discountsId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }


    public void setTotal(double price, int quantity) {
        this.total = price*quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", discountsId=" + discountsId +
                ", productName='" + productName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                ", productId=" + productId +
                '}';
    }
}
