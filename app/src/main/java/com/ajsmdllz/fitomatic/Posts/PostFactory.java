package com.ajsmdllz.fitomatic.Posts;

import com.ajsmdllz.fitomatic.Registration.Activities;
import com.ajsmdllz.fitomatic.Registration.User;

import java.util.ArrayList;

public class PostFactory {
    public Post createPost(User author, String title, ArrayList<User> followers, String location, String image, String description, ArrayList<String> activities, int likes) {
        if (location.equals("")) return new SingleActivity(author,title,description,activities.get(0),likes);
        else return new MultiActivity(author,title,followers,location,image,description,activities,likes);
    }
}
