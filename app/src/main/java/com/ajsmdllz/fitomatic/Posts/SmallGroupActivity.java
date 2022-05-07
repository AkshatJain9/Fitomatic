package com.ajsmdllz.fitomatic.Posts;

import com.ajsmdllz.fitomatic.Registration.User;

import java.util.ArrayList;

public class SmallGroupActivity extends Post {
    // This class will be used to create and handle posts
    // additional attributes on top of base Template
    private String activity;
    private String location; // As an address
    private ArrayList<String> followers;
    private int maxParticipants;
    private ArrayList<String> liked;

    public SmallGroupActivity(String author, String id, String title, String description, String date, String activity, String location, ArrayList<String> followers, int max, int likes, ArrayList<String> liked) {
        this.author = author;
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.activity = activity;
        this.location = location;
        this.followers = followers;
        this.maxParticipants = max;
        this.likes = likes;
        this.liked = liked;
    }
    // Getters
    public String getActivity() { return activity; }
    public String getLocation() { return location; }
    public ArrayList<String> getFollowers() { return followers; }
    public int getMaxParticipants() { return maxParticipants; }
    public ArrayList<String> getLiked() { return liked; }

    // Setters
    public void setActivity(String activity) { this.activity = activity; }
    public void setLocation(String location) { this.location = location; }
    public void setFollowers(ArrayList<String> followers) { this.followers = followers; }
    public void setMaxParticipants(int maxParticipants) { this.maxParticipants = maxParticipants; }
    public void setLiked(ArrayList<String> liked) { this.liked = liked; }
}
