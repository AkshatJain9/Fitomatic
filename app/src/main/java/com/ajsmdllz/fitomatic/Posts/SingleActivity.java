package com.ajsmdllz.fitomatic.Posts;

import com.ajsmdllz.fitomatic.Registration.User;

public class SingleActivity extends Post {

    // Unique to this concrete Post
    public String activity;

    public SingleActivity(User author, String title, String description, String activity, int likes) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.activity = activity;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivities() {
        return activity;
    }

    public void setActivities(String activity) {
        this.activity = activity;
    }

}
