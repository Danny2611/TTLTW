package com.example.finallaptrinhweb.model;

public class CategoryQuantity {
    private int categoryId;
    private int totalQuantity;

    public CategoryQuantity() {
    }

    public CategoryQuantity(int categoryId, int totalQuantity) {
        this.categoryId = categoryId;
        this.totalQuantity = totalQuantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "categoryId=" + categoryId +
                ", totalQuantity=" + totalQuantity;
    }
}
