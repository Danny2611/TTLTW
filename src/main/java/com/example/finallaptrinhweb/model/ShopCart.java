package com.example.finallaptrinhweb.model;

public class ShopCart {
    private int id;
    private int user_id;
    private int add_cart_id;

    public ShopCart(int id, int user_id, int add_cart_id) {
        this.id = id;
        this.user_id = user_id;
        this.add_cart_id = add_cart_id;
    }

    public ShopCart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAdd_cart_id() {
        return add_cart_id;
    }

    public void setAdd_cart_id(int add_cart_id) {
        this.add_cart_id = add_cart_id;
    }

    @Override
    public String toString() {
        return "ShopCart{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", add_cart_id=" + add_cart_id +
                '}';
    }
}
