package com.ajsmdllz.fitomatic;

public class Post {
    // This class will be used to create and handle posts
    private User author;
    private String title;
    private String location;
    private String image;

    public Post(User author, String title, String location, String image) {
        this.author = author;
        this.title = title;
        this.location = location;
        this.image = image;
    }


}
