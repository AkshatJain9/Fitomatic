package com.ajsmdllz.fitomatic.Posts;

import com.ajsmdllz.fitomatic.Registration.Activities;
import com.ajsmdllz.fitomatic.Registration.User;

public class SingleActivity extends Post {

    // This class will be used to create and handle posts
    public Activities activity;


    public SingleActivity(User author, String title, String image, String description, Activities activity, int likes) {
        this.author = author;
        this.title = title;
        this.image = image;
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

    public Activities getActivities() {
        return activity;
    }

    public void setActivities(Activities activity) {
        this.activity = activity;
    }

}
