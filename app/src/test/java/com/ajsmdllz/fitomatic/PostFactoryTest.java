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
    /**
     * With these tests the PostFactory achieves 100% Branch Coverage
     */
    private final ArrayList<String> singleActivity = new ArrayList<>(Arrays.asList("Soccer"));
    private final ArrayList<String> multiActivities = new ArrayList<>(Arrays.asList("Soccer", "AFL", "Golf"));
    private final ArrayList<String> followers = new ArrayList<>();
    private final ArrayList<String> likedBy = new ArrayList<>(Arrays.asList("Deni, Leon, Akshat"));

    @Test
    public void SingleActivityFactoryCreation() {
        PostFactory testFactory = new PostFactory();
        Post testPost = testFactory.createPost("Shaazaan","shzn123", "Test Post", "This is a single activity post test", "12/05/2022", singleActivity,"",followers,-1,0,3, likedBy);

        assertFalse("Post should be of type SingleActivity but got "+testPost.getClass(),testPost instanceof SmallGroupActivity);
        assertFalse("Post should be of type SingleActivity but got "+testPost.getClass(),testPost instanceof EventActivity);
        assertTrue("Post should be of type SingleActivity but got "+testPost.getClass(),testPost instanceof SingleActivity);
    }

    @Test
    public void SmallGroupActivityFactoryCreation() {
        PostFactory testFactory = new PostFactory();
        Post testPost = testFactory.createPost("Shaazaan","shzn123", "Test Small Group Post", "This is a small group activity post test", "12/05/2022", singleActivity,"Canberra",followers,-1,5,3, likedBy);

        assertFalse("Post should be of type SmallGroup but got "+testPost.getClass(),testPost instanceof SingleActivity);
        assertFalse("Post should be of type SmallGroup but got "+testPost.getClass(),testPost instanceof EventActivity);
        assertTrue("Post should be of type SmallGroup but got "+testPost.getClass(),testPost instanceof SmallGroupActivity);
    }

    @Test
    public void EventActivityFactoryCreation() {
        PostFactory testFactory = new PostFactory();
        Post testPost = testFactory.createPost("Shaazaan","shzn123", "Test Event Post", "This is an event activity post test", "13/05/2022", multiActivities,"Canberra",followers,10,20,3, likedBy);

        assertFalse("Post should be of type EventActivity but got "+testPost.getClass(),testPost instanceof SingleActivity);
        assertFalse("Post should be of type EventActivity but got "+testPost.getClass(),testPost instanceof SmallGroupActivity);
        assertTrue("Post should be of type EventActivity but got "+testPost.getClass(),testPost instanceof EventActivity);
    }

    @Test
    public void EventActivityFactoryCreationEdgeCase() {
        // Edge case: if Event only has 1 activity -> Should still be of type Event and not Single
        PostFactory testFactory = new PostFactory();
        Post testPost = testFactory.createPost("Shaazaan","shzn123", "Test Event Post", "This is an event activity post test", "13/05/2022", singleActivity,"Canberra",followers,10,20,3, likedBy);

        assertFalse("Post should be of type EventActivity but got "+testPost.getClass(),testPost instanceof SingleActivity);
        assertFalse("Post should be of type EventActivity but got "+testPost.getClass(),testPost instanceof SmallGroupActivity);
        assertTrue("Post should be of type EventActivity but got "+testPost.getClass(),testPost instanceof EventActivity);
    }
}
