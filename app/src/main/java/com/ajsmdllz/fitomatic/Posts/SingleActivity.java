package com.ajsmdllz.fitomatic.Posts;

import com.ajsmdllz.fitomatic.Registration.User;

public class SingleActivity extends Post {

    // Unique to this concrete Post
    private String activity;

    public SingleActivity(String author, String title, String description, String date, String activity, int likes) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.date = date;
        this.activity = activity;
        this.likes = likes;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
