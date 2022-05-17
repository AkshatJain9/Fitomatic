package com.ajsmdllz.fitomatic;

import static org.junit.Assert.assertEquals;

import com.ajsmdllz.fitomatic.Search.Expressions.Exp;
import com.ajsmdllz.fitomatic.Search.SearchParser;
import com.ajsmdllz.fitomatic.Search.SearchTokenizer;

import org.junit.Test;

public class ParserTest {

    /**
     * Initial Setup of Tokenizer and token strings
     */
    private static SearchTokenizer tokenizer;
    private static final String SIMPLE_CASE = "Steve@gmail.com";
    private static final String MID_CASE = "Bob@outlook.com, soccer, running, tennis, golf";
    private static final String COMPLEX_CASE1 = "Steve@gmail.com, running, myTitle    15/10/2023";
    private static final String COMPLEX_CASE2 = "Miranda@gmail.com, cycling, walking, karate, JoinMeInSomeAfternoonActivities    afternoon";

    private static final String[] testExample = new String[]{"Greg@gmail.com", "running", "running, walking, soccer", "14/05/2022", "Monday", "ThisIsATitle"};


    @Test
    public void testSimpleUser() {
        SearchTokenizer tok = new SearchTokenizer(SIMPLE_CASE);
        Exp userExp = new SearchParser(tok).parseStatement();
        assertEquals("UserQueryExpression{user='Steve@gmail.com', fields=EmptyExpression{}}", userExp.toString());

        SearchTokenizer tok1 = new SearchTokenizer(testExample[0]);
        Exp userExp1 = new SearchParser(tok1).parseStatement();
        assertEquals("UserQueryExpression{user='Greg@gmail.com', fields=EmptyExpression{}}", userExp1.toString());
    }

    @Test
    public void testSimpleActivities() {
        SearchTokenizer tok = new SearchTokenizer(testExample[1]);
        Exp actExp = new SearchParser(tok).parseStatement();
        assertEquals("ActivityQueryExpression{activity='Running', next=EmptyExpression{}}", actExp.toString());

        SearchTokenizer tok1 = new SearchTokenizer(testExample[2]);
        Exp actExp1 = new SearchParser(tok1).parseStatement();
        assertEquals("ActivityQueryExpression{activity='Running', " +
                                "next=ActivityQueryExpression{activity='Walking', " +
                                "next=ActivityQueryExpression{activity='Soccer', next=EmptyExpression{}}}}", actExp1.toString());
    }

    @Test
    public void testSimpleTitle() {
        SearchTokenizer tok = new SearchTokenizer(testExample[5]);
        Exp titleExp = new SearchParser(tok).parseStatement();
        assertEquals("PostQueryExpression{attribute='ThisIsATitle', furtherAttributes=EmptyExpression{}}", titleExp.toString());
    }

    @Test
    public void testSimpleTime() {
        SearchTokenizer tok = new SearchTokenizer(testExample[3]);
        Exp timeExp = new SearchParser(tok).parseStatement();
        assertEquals("TimeExpression{time='14/05/2022', next=EmptyExpression{}}", timeExp.toString());

        SearchTokenizer tok1 = new SearchTokenizer(testExample[4]);
        Exp timeExp1 = new SearchParser(tok1).parseStatement();
        assertEquals("TimeExpression{time='Monday', next=EmptyExpression{}}", timeExp1.toString());
    }

    @Test
    public void testMidCase() {
        SearchTokenizer tok = new SearchTokenizer(MID_CASE);
        Exp midExp = new SearchParser(tok).parseStatement();
        assertEquals("USER", midExp.show());
        assertEquals("UserQueryExpression{user='Bob@outlook.com', " +
                                "fields=ActivityQueryExpression{activity='Soccer', " +
                                    "next=ActivityQueryExpression{activity='Running', " +
                                    "next=ActivityQueryExpression{activity='Tennis', " +
                                    "next=ActivityQueryExpression{activity='Golf', next=EmptyExpression{}}}}}}", midExp.toString());
    }

    @Test
    public void testComplexCase1() {
        SearchTokenizer tok = new SearchTokenizer(COMPLEX_CASE1);
        Exp complexExp = new SearchParser(tok).parseStatement();
        assertEquals("USER", complexExp.show());
        assertEquals("UserQueryExpression{user='Steve@gmail.com', " +
                                "fields=ActivityQueryExpression{activity='Running', " +
                                    "next=PostQueryExpression{attribute='myTitle', " +
                                    "furtherAttributes=TimeExpression{time='15/10/2023', " + "next=EmptyExpression{}}}}}", complexExp.toString());
    }

    @Test
    public void testComplexCase2() {
        SearchTokenizer tok = new SearchTokenizer(COMPLEX_CASE2);
        Exp complexExp = new SearchParser(tok).parseStatement();
        assertEquals("USER", complexExp.show());
        assertEquals("UserQueryExpression{user='Miranda@gmail.com', " +
                                "fields=ActivityQueryExpression{activity='Cycling', " +
                                    "next=ActivityQueryExpression{activity='Walking', " +
                                    "next=ActivityQueryExpression{activity='Karate', " +
                                    "next=PostQueryExpression{attribute='JoinMeInSomeAfternoonActivities', " +
                                    "furtherAttributes=TimeExpression{time='afternoon', " + "next=EmptyExpression{}}}}}}}", complexExp.toString());
    }
}
