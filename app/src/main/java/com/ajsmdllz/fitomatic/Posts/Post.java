package com.ajsmdllz.fitomatic.Posts;

import java.util.ArrayList;

public abstract class Post {
    /**
     * The abstract Post class is the base for other concrete Post types
     * Posts will be utilised with the use of a PostFactory class
     */

    // These attributes are common among all concrete classes
    public String author;
    public String title;
    public String description;
    public String date;
    public String id;
    public int likes;
    public ArrayList<String> liked;

    // Setters
    public void setId(String id) {
        this.id = id;
    }
    public void setAuthor(String author) {
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
    public String getId() {
        return id;
    }
    public String getAuthor() {
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
