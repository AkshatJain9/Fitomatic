package com.ajsmdllz.fitomatic;

import java.util.ArrayList;

public class PostFactory {
    public Post createPost(User author, String title, ArrayList<User> followers, String location, String image, String description, ArrayList<Activities> activities, int likes) {
        if (location.equals("")) return new SingleActivity(author,title,image,description,activities.get(0),likes);
        else return new MultiActivity(author,title,followers,location,image,description,activities,likes);
    }
}
