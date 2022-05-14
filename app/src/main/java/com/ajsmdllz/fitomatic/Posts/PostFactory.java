package com.ajsmdllz.fitomatic.Posts;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Map;

public class PostFactory {
    /**
     * When instantiation:
     * SingleActivityPost has location set as empty (it does not have a location publicly listed)
     * SmallGroupActivity has price set to -1 (it does not require a payment)
     * Otherwise the Post created is of type EventActivity
     */
    public Post createPost(String author, String id, String title, String description, String date, ArrayList<String> activities, String location, ArrayList<String> followers, int price, int max, int likes, ArrayList<String> liked) {
        if (location.equals(""))
            return new SingleActivity(author, id, title, description, date, activities.get(0), likes, liked);
        else if (price == -1)
            return new SmallGroupActivity(author, id, title, description, date, activities.get(0), location, followers, max, likes, liked);
        else
            return new EventActivity(author, id, title, description, date, activities, location, followers, price, max, likes, liked);
    }


    public Post createPostfromDB(DocumentSnapshot d) {
        Post p;
        Map<String, Object> map = d.getData();
        if (map.keySet().size() == 8) {
            p = new SingleActivity(
                    (String) map.get("author"),
                    (String) map.get("id"),
                    (String) map.get("title"),
                    (String) map.get("description"),
                    (String) map.get("date"),
                    (String) map.get("activity"),
                    ((Long) map.get("likes")).intValue(),
                    (ArrayList<String>) map.get("liked"));
        } else if (map.keySet().size() == 11) {
            p = new SmallGroupActivity(
                    (String) map.get("author"),
                    (String) map.get("id"),
                    (String) map.get("title"),
                    (String) map.get("description"),
                    (String) map.get("date"),
                    (String) map.get("activity"),
                    (String) map.get("location"),
                    (ArrayList<String>) map.get("followers"),
                    ((Long) map.get("maxParticipants")).intValue(),
                    ((Long) map.get("likes")).intValue(),
                    (ArrayList<String>) map.get("liked"));
        } else {
            p = new EventActivity(
                    (String) map.get("author"),
                    (String) map.get("id"),
                    (String) map.get("title"),
                    (String) map.get("description"),
                    (String) map.get("date"),
                    (ArrayList<String>) map.get("activities"),
                    (String) map.get("location"),
                    (ArrayList<String>) map.get("followers"),
                    ((Long) map.get("price")).intValue(),
                    ((Long) map.get("maxParticipants")).intValue(),
                    ((Long) map.get("likes")).intValue(),
                    (ArrayList<String>) map.get("liked"));
        }
        return p;
    }
}
