package com.example.finallaptrinhweb.model;


public class SocialMedia {
    private int id;
    private String facebook;
    private String instagram;
    private String twitter;
    private String pinterest;
    private String youtube;
    private String linkedin;

    // Constructors
    public SocialMedia() {
    }

    public SocialMedia(String facebook, String instagram, String twitter, String pinterest, String youtube, String linkedin) {
        this.facebook = facebook;
        this.instagram = instagram;
        this.twitter = twitter;
        this.pinterest = pinterest;
        this.youtube = youtube;
        this.linkedin = linkedin;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getPinterest() {
        return pinterest;
    }

    public void setPinterest(String pinterest) {
        this.pinterest = pinterest;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "SocialMedia{" +
                "id=" + id +
                ", facebook='" + facebook + '\'' +
                ", instagram='" + instagram + '\'' +
                ", twitter='" + twitter + '\'' +
                ", pinterest='" + pinterest + '\'' +
                ", youtube='" + youtube + '\'' +
                ", linkedin='" + linkedin + '\'' +
                '}';
    }
}
