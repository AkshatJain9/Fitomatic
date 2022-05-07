package com.ajsmdllz.fitomatic.Posts;

import java.util.ArrayList;

public class PostFactory {
    public Post createPost(String author, String id, String title, String description, String date, ArrayList<String> activities, String location, ArrayList<String> followers, int price, int max, int likes, ArrayList<String> liked) {
        if (location.equals(""))
            return new SingleActivity(author, id, title, description, date, activities.get(0), likes, liked);
        else if (activities.size() == 1)
            return new SmallGroupActivity(author, id, title, description, date, activities.get(0), location, followers, max, likes, liked);
        else
            return new EventActivity(author, id, title, description, date, activities, location, followers, price, max, likes, liked);
    }
}
