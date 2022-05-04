package com.ajsmdllz.fitomatic.Posts;

import com.ajsmdllz.fitomatic.Registration.User;

import java.util.ArrayList;

public class PostFactory {
    public Post createPost(User author, String title, String description, String date, ArrayList<String> activities, String location, String image, ArrayList<String> followers, int max, int likes) {
        if (location.equals("")) return new SingleActivity(author,title,description, date, activities.get(0),likes);
        else return new SmallGroupActivity(author,title,description,date,activities.get(0),location,image,followers,max,likes);
    }
}
