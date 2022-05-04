package com.ajsmdllz.fitomatic.Posts;

import com.ajsmdllz.fitomatic.Registration.User;

import java.util.ArrayList;

public abstract class Post {

    // These attributes are common among all concrete classes
    public User author;
    public String title;
    public String description;
    public int likes;
}
