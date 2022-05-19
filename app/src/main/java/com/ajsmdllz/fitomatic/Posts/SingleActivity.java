package com.ajsmdllz.fitomatic.Posts;

import java.util.ArrayList;

public class SingleActivity extends Post {
    /**
     * This class will be used to create and handle one-on-one Posts
     * targeted for activities between individuals
     */
    // Unique to this concrete Post
    private String activity;

    public SingleActivity(String author, String id, String title, String description, String date, String activity, int likes, ArrayList<String> liked) {
        this.author = author;
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.activity = activity;
        this.likes = likes;
        this.liked = liked;
    }

    public String getActivity() {
        return activity;
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }
}
