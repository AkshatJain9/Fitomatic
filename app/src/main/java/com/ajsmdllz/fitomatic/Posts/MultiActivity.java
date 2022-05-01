package com.ajsmdllz.fitomatic.Posts;

import com.ajsmdllz.fitomatic.Registration.Activities;
import com.ajsmdllz.fitomatic.Registration.User;

import java.util.ArrayList;

public class MultiActivity extends Post {
    // This class will be used to create and handle posts
    // additional attributes on top of base Template
    private String location; // As an address
    public ArrayList<Activities> activities;


    public MultiActivity(User author, String title, ArrayList<User> followers, String location, String image, String description, ArrayList<Activities> activities, int likes) {
        this.author = author;
        this.title = title;
        this.followers = followers;
        this.location = location;
        this.image = image;
        this.description = description;
        this.activities = activities;
        this.likes = likes;
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

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
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
