package com.ajsmdllz.fitomatic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.ajsmdllz.fitomatic.Posts.EventActivity;
import com.ajsmdllz.fitomatic.Posts.Post;
import com.ajsmdllz.fitomatic.Posts.PostFactory;
import com.ajsmdllz.fitomatic.Posts.SingleActivity;
import com.ajsmdllz.fitomatic.Posts.SmallGroupActivity;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class AVLPostsTest {
    private final Post pSingle1 = new SingleActivity("p", "p", "p", "p", "date", "activity",0,new ArrayList<>());
    private final Post pSingle2 = new SingleActivity("p", "p", "p", "p", "date", "activity",6,new ArrayList<>());
    private final Post pSingle3 = new SingleActivity("p", "p", "p", "p", "date", "activity",2,new ArrayList<>());
    private final Post pSmall1 = new SmallGroupActivity("p", "p", "p", "p", "date", "activity","location",new ArrayList<>(),10,3,new ArrayList<>());
    private final Post pSmall2 = new SmallGroupActivity("p", "p", "p", "p", "date", "activity","location",new ArrayList<>(),10,18,new ArrayList<>());
    private final Post pSmall3 = new SmallGroupActivity("p", "p", "p", "p", "date", "activity","location",new ArrayList<>(),10,27,new ArrayList<>());
    private final Post pEvent1 = new EventActivity("p", "p", "p", "p", "date", new ArrayList<>(),"location",new ArrayList<>(),0,10,4,new ArrayList<>());
    private final Post pEvent2 = new EventActivity("p", "p", "p", "p", "date", new ArrayList<>(),"location",new ArrayList<>(),0,10,16,new ArrayList<>());
    private final Post pEvent3 = new EventActivity("p", "p", "p", "p", "date", new ArrayList<>(),"location",new ArrayList<>(),0,10,29,new ArrayList<>());
    private final Post pEvent4 = new EventActivity("p", "p", "p", "p", "date", new ArrayList<>(),"location",new ArrayList<>(),0,10,1,new ArrayList<>());

    @Test
    public void nullTest() {
        AVLPosts avl = new AVLPosts();
        avl = avl.insert(null);
        assertEquals(new ArrayList<>(),avl.iterator());
    }

    @Test
    public void heightTest() {
        AVLPosts avl = new AVLPosts();
        avl.insert(pSingle2);
        assertEquals(0,avl.getHeight());
        avl.insert(pSingle1);
        assertEquals(1,avl.getHeight());
        avl.insert(pSmall1);
        avl.insert(pSmall2);
        avl.insert(pSmall3);
        avl.insert(pEvent1);
        avl.insert(pEvent2);
        assertEquals(3,avl.getHeight());
    }

    @Test
    public void insertSortedTest() {
        ArrayList<Post> postArrayList = new ArrayList<>();
        AVLPosts avl = new AVLPosts();
        // Tests array is the same as what we want
        postArrayList.add(pSingle1);
        avl.insert(pSingle1);
        assertEquals(postArrayList, avl.iterator());

        // Testing that it is in correct order
        avl.insert(pEvent1);
        assertEquals(pEvent1, avl.iterator().get(0));
        assertEquals(pSingle1, avl.iterator().get(1));
    }

    @Test
    public void complexInsertTests() {
        ArrayList<Post> postArrayList1 = new ArrayList<>(Arrays.asList(pEvent3,pSmall3,pSmall2,pEvent2,pEvent1,pSmall1,pSingle3,pEvent4));
        AVLPosts avl1 = new AVLPosts();
        avl1 = avl1.insert(pSmall1);
        avl1 = avl1.insert(pSmall2);
        avl1 = avl1.insert(pSmall3);
        avl1 = avl1.insert(pSingle3);
        avl1 = avl1.insert(pEvent4);
        avl1 = avl1.insert(pEvent1);
        avl1 = avl1.insert(pEvent2);
        avl1 = avl1.insert(pEvent3);
        assertEquals(postArrayList1, avl1.iterator());
        assertEquals(3, avl1.getHeight());
    }

}
