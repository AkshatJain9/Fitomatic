package com.ajsmdllz.fitomatic;

import java.util.ArrayList;

public class Post {
    // This class will be used to create and handle posts
    private User author;
    private String title;
    private String location; // As an address
    private String image;
    private String description;
    private ArrayList<Activities> activities;
    private int likes;


    public Post(User author, String title, String location, String image, String description, ArrayList<Activities> activities, int likes) {
        this.author = author;
        this.title = title;
        this.location = location;
        this.image = image;
        this.description = description;
        this.activities = activities;
        this.likes = 0;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Activities> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activities> activities) {
        this.activities = activities;
    }


}
