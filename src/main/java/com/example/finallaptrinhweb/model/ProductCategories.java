package com.example.finallaptrinhweb.model;

public class ProductCategories {
    private int id;
    private String categoryName;
    private int group_id;
    private boolean active;

    public ProductCategories() {
    }

    public ProductCategories(int id, String categoryName, int group_id, boolean active) {
        this.id = id;
        this.categoryName = categoryName;
        this.group_id = group_id;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ProductCategories{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", group_id=" + group_id +
                ", active=" + active +
                '}';
    }
}
