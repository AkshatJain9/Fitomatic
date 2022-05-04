package com.ajsmdllz.fitomatic.Posts;

import com.ajsmdllz.fitomatic.Registration.User;

import java.util.ArrayList;

public class SmallGroupActivity extends Post {
    // This class will be used to create and handle posts
    // additional attributes on top of base Template
    private String activity;
    private String location; // As an address
    private String imageRef; // Stored as string for now
    private ArrayList<String> followers;
    private int maxParticipants;


    public SmallGroupActivity(User author, String title, String description, String date, String activity, String location, String image, ArrayList<String> followers, int max, int likes) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.date = date;
        this.activity = activity;
        this.location = location;
        this.imageRef = image;
        this.followers = followers;
        this.maxParticipants = max;
        this.likes = likes;
    }

    // Setters
    public void setActivity(String activity) {
        this.activity = activity;
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
    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    // Getters
    public String getActivity() {
        return activity;
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
    public int getMaxParticipants() {
        return maxParticipants;
    }
}
