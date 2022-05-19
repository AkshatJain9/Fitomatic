package com.ajsmdllz.fitomatic.Posts;

import java.util.ArrayList;

public class EventActivity extends Post {
    /**
     * This class will be used to create and handle larger Posts
     * targeted for big events and fundraisers
     */
    // Additional attributes on top of base Template
    private ArrayList<String> activities;
    private String location; // As an address
    private ArrayList<String> followers;
    private int price;
    private int maxParticipants;


    public EventActivity(String author, String id, String title, String description, String date, ArrayList<String> activities, String location, ArrayList<String> followers, int price, int max, int likes, ArrayList<String> liked) {
        this.author = author;
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.activities = activities;
        this.location = location;
        this.followers = followers;
        this.price = price;
        this.maxParticipants = max;
        this.likes = likes;
        this.liked = liked;
    }

    // Setters
    public void setActivities(ArrayList<String> activities) {
        this.activities = activities;
    }
    public void setLocation(String location) {
        this.location = location;
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
