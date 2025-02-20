package com.example.finallaptrinhweb.model;

public class AddCart {
    private int id;
    private int product_id;
    private int addcartDate;

    public AddCart() {
    }

    public AddCart(int id, int product_id, int addcartDate) {
        this.id = id;
        this.product_id = product_id;
        this.addcartDate = addcartDate;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getAddcartDate() {
        return addcartDate;
    }

    public void setAddcartDate(int addcartDate) {
        this.addcartDate = addcartDate;
    }

    @Override
    public String toString() {
        return "AddCart{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", addcartDate=" + addcartDate +
                '}';
    }
}
