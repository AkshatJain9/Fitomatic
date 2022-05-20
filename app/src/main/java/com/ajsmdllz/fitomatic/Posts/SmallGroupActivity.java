package com.ajsmdllz.fitomatic.Posts;

import java.util.ArrayList;

public class SmallGroupActivity extends Post {
    /**
     * This class will be used to create and handle small group Posts
     * targeted for activities between informal groups
     */
    // additional attributes on top of base abstract Post
    private String activity;
    private String location; // As an address
    private ArrayList<String> followers;
    private int maxParticipants;

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

    // Setters
    public void setActivity(String activity) { this.activity = activity; }
    public void setLocation(String location) { this.location = location; }
    public void setFollowers(ArrayList<String> followers) { this.followers = followers; }
    public void setMaxParticipants(int maxParticipants) { this.maxParticipants = maxParticipants; }
}
