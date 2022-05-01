package com.ajsmdllz.fitomatic.Posts;

import com.ajsmdllz.fitomatic.Registration.User;

import java.util.ArrayList;

public abstract class Post {

    // These attributes are common among both concrete classes
    public User author;
    public String title;
    public String image;
    public String description;
    public ArrayList<User> followers;
    public int likes;
}
