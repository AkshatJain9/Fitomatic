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
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public void addPosts(Post p) {
        this.posts.add(p);
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
