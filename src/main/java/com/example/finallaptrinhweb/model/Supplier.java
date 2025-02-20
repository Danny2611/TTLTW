package com.example.finallaptrinhweb.model;

public class Supplier {
    private int id;
    private String supplierName;
    private String contactName;
    private String email;
    private String phone;
    private String detailAddress;
    private String imageUrl;
    private int productId;

    public Supplier() {
    }

    public Supplier(int id, String supplierName, String contactName, String email, String phone, String detailAddress, String imageUrl, int productId) {
        this.id = id;
        this.supplierName = supplierName;
        this.contactName = contactName;
        this.email = email;
        this.phone = phone;
        this.detailAddress = detailAddress;
        this.imageUrl = imageUrl;
        this.productId = productId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetailAddress() {
        return this.detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Supplier{id=" + this.id + ", supplierName='" + this.supplierName + "', contactName='" + this.contactName + "', email='" + this.email + "', phone='" + this.phone + "', detailAddress='" + this.detailAddress + "', imageUrl='" + this.imageUrl + "', productId=" + this.productId + "}";
    }
}
