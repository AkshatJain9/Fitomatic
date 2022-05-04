package com.ajsmdllz.fitomatic.Posts;

import com.ajsmdllz.fitomatic.Registration.User;

import java.util.ArrayList;

public abstract class Post {

    // These attributes are common among all concrete classes
    public User author;
    public String title;
    public String description;
    public String date;
    public int likes;

    // Setters
    public void setAuthor(User author) {
        this.author = author;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }

    // Getters
    public User getAuthor() {
        return author;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getDate() {
        return date;
    }
    public int getLikes() {
        return likes;
    }
}
