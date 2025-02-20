package com.example.finallaptrinhweb.model;

import java.sql.Timestamp;

public class Feedback {
    private int id;  // Thêm trường id
    private String email;
    private String name;
    private String content;
    private Timestamp submissionDate;

    public Feedback() {
    }

    public Feedback(int id, String email, String name, String content, Timestamp submissionDate) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.content = content;
        this.submissionDate = submissionDate;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSubmissionDate() {
        return this.submissionDate;
    }

    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String toString() {
        return "Feedback{ id=" + this.id + ", email='" + this.email + "', name='" + this.name + "', content='" + this.content + "', submissionDate=" + this.submissionDate + "}";
    }
}
