
package com.example.finallaptrinhweb.model;

import java.sql.Timestamp;

public class ProductReview {
    private int id;
    private int userId;
    private int rating;
    private int orderId;
    private String reviewText;
    private Timestamp reviewDate;
    private String imageUrl;

    public ProductReview() {
    }

    public ProductReview(int id, int userId, int rating, int orderId, String reviewText, Timestamp reviewDate, String imageUrl) {
        this.id = id;
        this.userId = userId;
        this.rating = rating;
        this.orderId = orderId;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getReviewText() {
        return this.reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Timestamp getReviewDate() {
        return this.reviewDate;
    }

    public void setReviewDate(Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String toString() {
        return "ProductReview{id=" + this.id + ", userId=" + this.userId + ", rating=" + this.rating + ", orderId=" + this.orderId + ", reviewText='" + this.reviewText + "', reviewDate=" + this.reviewDate + ", imageUrl='" + this.imageUrl + "'}";
    }
}
