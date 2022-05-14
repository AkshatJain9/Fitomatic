package com.ajsmdllz.fitomatic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.ajsmdllz.fitomatic.Search.SearchTokenizer;
import com.ajsmdllz.fitomatic.Search.Token;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TokenizerTest {
    @Test
    public void userTest() {
        SearchTokenizer searchTokenizer = new SearchTokenizer("@Steve@gmail.com");
        assertEquals(new ArrayList<>(Collections.singletonList(new Token("@Steve@gmail.com", Token.Type.NAME))), searchTokenizer.tokenize());
    }

    @Test
    public void activityTest() {
        SearchTokenizer searchTokenizer = new SearchTokenizer("running");
        assertEquals(new ArrayList<>(Collections.singletonList(new Token("running", Token.Type.ACTIVITY))), searchTokenizer.tokenize());
        SearchTokenizer searchTokenizer1 = new SearchTokenizer("running swimming,   walking");
        assertEquals(new ArrayList<>(Arrays.asList(new Token("running", Token.Type.ACTIVITY), new Token("swimming", Token.Type.ACTIVITY),new Token("walking", Token.Type.ACTIVITY))), searchTokenizer1.tokenize());
    }

    @Test
    public void timeTest() {
        SearchTokenizer searchTokenizer = new SearchTokenizer("14/05/2022");
        assertEquals(new ArrayList<>(Collections.singletonList(new Token("14/05/2022", Token.Type.TIME))), searchTokenizer.tokenize());
    }

    @Test
    public void titleTest() {
        SearchTokenizer searchTokenizer = new SearchTokenizer("CanberraRun");
        assertEquals(new ArrayList<>(Collections.singletonList(new Token("CanberraRun", Token.Type.TITLE))), searchTokenizer.tokenize());
        SearchTokenizer searchTokenizer1 = new SearchTokenizer("Let's go for a run!");
        assertEquals(new ArrayList<>(Collections.singletonList(new Token("Let's go for a run!", Token.Type.TITLE))), searchTokenizer1.tokenize());
    }

    @Test
    public void combinedTest() {
        SearchTokenizer searchTokenizer = new SearchTokenizer("@Steve@gmail.com, running, 15/10/2023    myTitle");
        assertEquals(new ArrayList<>(Arrays.asList(new Token("@Steve@gmail.com", Token.Type.NAME),new Token("running", Token.Type.ACTIVITY),new Token("15/10/2023", Token.Type.TIME),new Token("myTitle", Token.Type.TITLE))), searchTokenizer.tokenize());
    }
}
