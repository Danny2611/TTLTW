package com.example.finallaptrinhweb.model;

public class CartItem {
    private Product product;
    private int quantity;
    private double totalPrice;

    public CartItem() {
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return this.product.getPrice() * this.quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean incre(int prold, int quantity) {
        this.quantity += quantity;
        return true;
    }

    public boolean decre(int prold, int quantity) {
        this.quantity -= quantity;
        if (this.getQuantity() <= 0) {
            this.quantity = 0;
        }
        return true;
    }
    public OrderProduct toOrderProduct() {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductName(product.getProductName());
        orderProduct.setQuantity(quantity);
        orderProduct.setPrice(product.getPrice());
        // Set other properties as needed

        return orderProduct;
    }
    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
