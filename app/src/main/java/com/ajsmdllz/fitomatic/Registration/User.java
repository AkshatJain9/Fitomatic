package com.ajsmdllz.fitomatic.Registration;

import com.ajsmdllz.fitomatic.P2PMessaging.Message;
import com.ajsmdllz.fitomatic.Posts.Post;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String bio;
    private int age;
    private String gender;
    private ArrayList<String> interests;
    private ArrayList<Post> posts;
    private ArrayList<String> blocked;
    private HashMap<String, ArrayList<Message>> messages;
    private ArrayList<Post> following;

    // Used for Database Casting, do not delete
    public User() {}

    public User(String firstname, String lastname, String email, String bio, int age, String gender, ArrayList<String> interests, ArrayList<Post> posts, ArrayList<String> blocked, HashMap<String, ArrayList<Message>> messages, ArrayList<Post> following) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.bio = bio;
        this.age = age;
        this.gender = gender;
        this.interests = interests;
        this.posts = posts;
        this.blocked = blocked;
        this.messages = messages;
        this.following = following;
    }

    public User(String firstname, String lastname, String email, String bio, int age, String gender, ArrayList<String> interests, ArrayList<Post> posts) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.bio = bio;
        this.age = age;
        this.gender = gender;
        this.interests = interests;
        this.posts = posts;
    }


    public User(String email) {
        this.email = email;
        blocked = new ArrayList<>();
        messages = new HashMap<>();
        following = new ArrayList<>();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public String getFirstname() {return firstname;}

    public String getLastname() {return lastname;}
}
