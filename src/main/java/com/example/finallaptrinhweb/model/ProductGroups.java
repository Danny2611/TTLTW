package com.example.finallaptrinhweb.model;

public class ProductGroups {
    private int id;
    private String groupName;
    private boolean active;

    public ProductGroups(int id, String groupName, boolean active) {
        this.id = id;
        this.groupName = groupName;
        this.active = active;
    }

    public ProductGroups() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ProductGroups{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", active=" + active +
                '}';
    }
}
