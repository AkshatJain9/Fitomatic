package com.ajsmdllz.fitomatic.Posts;

import java.util.ArrayList;

public class PostFactory {
    public Post createPost(String author, String id, String title, String description, String date, ArrayList<String> activities, String location, String image, ArrayList<String> followers, int price, int max, int likes) {
        if (location.equals(""))
            return new SingleActivity(author, id, title, description, date, activities.get(0), likes);
        else if (activities.size() == 1)
            return new SmallGroupActivity(author,id,title,description,date,activities.get(0),location,image,followers,max,likes);
        else
            return new EventActivity(author,id,title,description,date,activities,location,image,followers,price,max,likes);
    }
}
