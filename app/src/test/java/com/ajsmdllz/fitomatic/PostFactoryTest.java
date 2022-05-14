package com.ajsmdllz.fitomatic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.ajsmdllz.fitomatic.Posts.EventActivity;
import com.ajsmdllz.fitomatic.Posts.Post;
import com.ajsmdllz.fitomatic.Posts.PostFactory;
import com.ajsmdllz.fitomatic.Posts.SingleActivity;
import com.ajsmdllz.fitomatic.Posts.SmallGroupActivity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PostFactoryTest {
    private final ArrayList<String> activities = new ArrayList<>(Arrays.asList("Soccer"));
    private final ArrayList<String> followers = new ArrayList<>();
    private final ArrayList<String> likedBy = new ArrayList<>(Arrays.asList("Deni, Leon, Akshat"));

    @Test
    public void SingleActivityFactoryCreation() {
        PostFactory testFactory = new PostFactory();
        Post testPost = testFactory.createPost("Shaazaan","shzn123", "Test Post", "This is a single activity post test", "12/05/2022", activities,"",followers,0,0,3, likedBy);

        assertFalse("Post should be of type SingleActivity but got "+testPost.getClass(),testPost instanceof SmallGroupActivity);
        assertFalse("Post should be of type SingleActivity but got "+testPost.getClass(),testPost instanceof EventActivity);
        assertTrue("Post should be of type SingleActivity but got "+testPost.getClass(),testPost instanceof SingleActivity);
    }

    @Test
    public void SmallGroupActivityFactoryCreation() {
        PostFactory testFactory = new PostFactory();
        Post testPost = testFactory.createPost("Shaazaan","shzn123", "Test Small Group Post", "This is a small group activity post test", "12/05/2022", activities,"Canberra",followers,0,5,3, likedBy);

        assertFalse("Post should be of type SingleActivity but got "+testPost.getClass(),testPost instanceof SingleActivity);
        assertFalse("Post should be of type SingleActivity but got "+testPost.getClass(),testPost instanceof EventActivity);
        assertTrue("Post should be of type SingleActivity but got "+testPost.getClass(),testPost instanceof SmallGroupActivity);
    }
}
