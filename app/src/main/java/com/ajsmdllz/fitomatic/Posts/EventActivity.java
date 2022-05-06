package com.ajsmdllz.fitomatic.Posts;

import com.ajsmdllz.fitomatic.Registration.User;

import java.util.ArrayList;

public class EventActivity extends Post {
    // This class will be used to create and handle posts
    // additional attributes on top of base Template
    private ArrayList<String> activities;
    private String location; // As an address
    private String imageRef; // Stored as string for now
    private ArrayList<String> followers;
    private int price;
    private int maxParticipants;


    public EventActivity(String author, String id, String title, String description, String date, ArrayList<String> activities, String location, String image, ArrayList<String> followers, int price, int max, int likes) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.date = date;
        this.activities = activities;
        this.location = location;
        this.imageRef = image;
        this.followers = followers;
        this.price = price;
        this.maxParticipants = max;
        this.likes = likes;
    }

    // Setters
    public void setActivities(ArrayList<String> activities) {
        this.activities = activities;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }
    public void setFollowers(ArrayList<String> followers) {
        this.followers = followers;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    // Getters
    public ArrayList<String> getActivities() {
        return activities;
    }
    public String getLocation() {
        return location;
    }
    public String getImageRef() {
        return imageRef;
    }
    public ArrayList<String> getFollowers() {
        return followers;
    }
    public int getPrice() {
        return price;
    }
    public int getMaxParticipants() {
        return maxParticipants;
    }
}
