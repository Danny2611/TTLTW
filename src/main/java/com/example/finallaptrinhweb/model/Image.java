
package com.example.finallaptrinhweb.model;

public class Image {
    private int id;
    private int productId;
    private int reviewId;
    private String imageUrl;

    public Image() {
    }

    public Image(int id, int productId, int reviewId, String imageUrl) {
        this.id = id;
        this.productId = productId;
        this.reviewId = reviewId;
        this.imageUrl = imageUrl;
    }

    public String toString() {
        return "Image{id=" + this.id + ", productId=" + this.productId + ", reviewId=" + this.reviewId + ", imageUrl='" + this.imageUrl + "'}";
    }
}
