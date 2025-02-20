
package com.example.finallaptrinhweb.model;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String productName;
    private int categoryId;
    private double price;
    private int quantity;
    private String purpose;
    private String contraindications;
    private int stockQuantity;
    private String ingredients;
    private String dosage;
    private String instructions;
    private String warrantyPeriod;
    private String productType;
    private String storageCondition;
    private int supplierId;
    private String imageUrl;
    private boolean active;
    private String supplierImageUrl;

    public Product() {
    }

    public Product(int id, String productName, int categoryId, double price, int quantity, String purpose, String contraindications, int stockQuantity, String ingredients, String dosage, String instructions, String warrantyPeriod, String productType, String storageCondition, int supplierId, String imageUrl, boolean active, String supplierImageUrl) {
        this.id = id;
        this.productName = productName;
        this.categoryId = categoryId;
        this.price = price;
        this.quantity = quantity;
        this.purpose = purpose;
        this.contraindications = contraindications;
        this.stockQuantity = stockQuantity;
        this.ingredients = ingredients;
        this.dosage = dosage;
        this.instructions = instructions;
        this.warrantyPeriod = warrantyPeriod;
        this.productType = productType;
        this.storageCondition = storageCondition;
        this.supplierId = supplierId;
        this.imageUrl = imageUrl;
        this.active = active;
        this.supplierImageUrl = supplierImageUrl;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStockQuantity() {
        return this.stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDosage() {
        return this.dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }


    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getProductType() {
        return this.productType;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getSupplierId() {
        return this.supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setStorageCondition(String storageCondition) {
        this.storageCondition = storageCondition;
    }

    public String getStorageCondition() {
        return storageCondition;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSupplierImageUrl() {
        return this.supplierImageUrl;
    }

    public void setSupplierImageUrl(String supplierImageUrl) {
        this.supplierImageUrl = supplierImageUrl;
    }

    public int getTypeWeight() {
        // Assuming productType is a String field indicating the type of the product.
        // You can customize this logic based on your specific requirements.

        if ("TypeA".equals(productType)) {
            return 1;
        } else if ("TypeB".equals(productType)) {
            return 2;
        } else if ("TypeC".equals(productType)) {
            return 3;
        } else {
            // Default type weight if none of the conditions are met.
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", categoryId=" + categoryId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", purpose='" + purpose + '\'' +
                ", contraindications='" + contraindications + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", ingredients='" + ingredients + '\'' +
                ", dosage='" + dosage + '\'' +
                ", instructions='" + instructions + '\'' +
                ", warrantyPeriod='" + warrantyPeriod + '\'' +
                ", productType='" + productType + '\'' +
                ", storageCondition='" + storageCondition + '\'' +
                ", supplierId=" + supplierId +
                ", imageUrl='" + imageUrl + '\'' +
                ", active=" + active +
                '}';
    }
}
