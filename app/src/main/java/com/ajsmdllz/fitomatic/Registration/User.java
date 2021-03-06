package com.ajsmdllz.fitomatic.Registration;

import com.ajsmdllz.fitomatic.P2PMessaging.Message;

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
    private ArrayList<String> posts;
    private ArrayList<String> blocked;
    private HashMap<String, ArrayList<Message>> messages;
    private ArrayList<String> following;

    // Used for Database Casting, do not delete
    public User() {}

    public User(String firstname, String lastname, String email, String bio, int age, String gender, ArrayList<String> interests, ArrayList<String> posts, ArrayList<String> blocked, HashMap<String, ArrayList<Message>> messages, ArrayList<String> following) {
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

    public User(String email) {
        this.email = email;
        this.firstname = "";
        this.lastname = "";
        this.blocked = new ArrayList<>();
        this.messages = new HashMap<>();
        this.following = new ArrayList<>();
        this.bio = "";
        this.age = 0;
        this.gender = "";
        this.interests = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.blocked = new ArrayList<>();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getFirstname() {return firstname;}

    public String getLastname() {return lastname;}
}
