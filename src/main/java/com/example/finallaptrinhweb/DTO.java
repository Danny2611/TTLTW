package com.example.finallaptrinhweb;

import com.example.finallaptrinhweb.model.Comment;
import com.example.finallaptrinhweb.model.Product;
import com.example.finallaptrinhweb.model.User;

public class DTO {
    private User user;
    private Comment  comment;
    private Product product;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public DTO(User user, Comment comment, Product product) {
        this.user = user;
        this.comment = comment;
        this.product = product;
    }
}
