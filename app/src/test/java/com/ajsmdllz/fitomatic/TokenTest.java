package com.ajsmdllz.fitomatic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import com.ajsmdllz.fitomatic.Search.Token;

import org.junit.Test;

public class TokenTest {
    private final Token shznToken = new Token("shzn@gmail.com", Token.Type.NAME);
    private final Token shzn1Token = new Token("shzn@gmail.com", Token.Type.NAME);
    private final Token runningToken = new Token("running", Token.Type.ACTIVITY);
    private final Token titleToken = new Token("ThisIsMyTitle", Token.Type.TITLE);
    private final Token timeTitle = new Token("14/05/2022", Token.Type.TIME);



    @Test
    public void getTokenTest() {
        assertEquals("shzn@gmail.com", shznToken.getToken());
        assertEquals("running", runningToken.getToken());
        assertEquals("ThisIsMyTitle", titleToken.getToken());
        assertEquals("14/05/2022", timeTitle.getToken());
    }

    @Test
    public void getTypeTest() {
        assertEquals(Token.Type.NAME, shznToken.getType());
        assertEquals(Token.Type.ACTIVITY, runningToken.getType());
        assertEquals(Token.Type.TITLE, titleToken.getType());
        assertEquals(Token.Type.TIME, timeTitle.getType());
    }

    @Test
    public void equalsTest() {
        assertNull(null);
        assertEquals(shznToken, shzn1Token);
        assertEquals(shznToken, shznToken);
        assertNotEquals(shznToken, titleToken);
        assertNotEquals(timeTitle, titleToken);
        assertNotEquals(runningToken, titleToken);
    }

    @Test
    public void toStringTest() {
        assertEquals("NAME( NAME: shzn@gmail.com)", shznToken.toString());
        assertEquals("ACTIVITY( ACTIVITY: running)", runningToken.toString());
        assertEquals("TITLE( TITLE: ThisIsMyTitle)", titleToken.toString());
        assertEquals("TIME( TIME: 14/05/2022)", timeTitle.toString());
    }
}