
package com.example.finallaptrinhweb.model;

public class Category {
    private int id;
    private String categoryName;
    private boolean active;

    public Category() {
    }

    public Category(int id, String categoryName, boolean active) {
        this.id = id;
        this.categoryName = categoryName;
        this.active = active;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String toString() {
        return "Category{id=" + this.id + ", categoryName='" + this.categoryName + "', active=" + this.active + "}";
    }
}
