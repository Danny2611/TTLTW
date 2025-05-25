package com.example.finallaptrinhweb.model;

import java.time.LocalDate;

public class Comment {
    private Long id;
    private String name;
    private int userId;
    private  Long productId;
    private int star;
    private  String content;
    private LocalDate  createdAt;
    private String formattedCreatedAt;

    public String getFormattedCreatedAt() {
        return formattedCreatedAt;
    }

    public void setFormattedCreatedAt(String formattedCreatedAt) {
        this.formattedCreatedAt = formattedCreatedAt;
    }


    public Comment(

    ) {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getProductId() {
        return productId;
    }

    public int getStar() {
        return star;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productId=" + productId +
                ", star=" + star +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
