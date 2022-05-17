package com.ajsmdllz.fitomatic.Search;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchTokenizer {
    private String search;
    private String[] searchElems;
    private ArrayList<Token> tokenised;
    private int iteratorindex = 0;


    public SearchTokenizer(String input) {
        this.search = input;
        this.searchElems = input.split("[ ,]+");
        this.tokenised = tokenize();
    }

    /**
     * Given the input search string in array form, segment them into a sequential Token Array
     * @return ArrayList of Tokens
     */
    public ArrayList<Token> tokenize() {
        ArrayList<Token> tokens = new ArrayList<>();
        for (String s : searchElems) {
            if (KeywordList.ActivityList.contains(s.trim().toLowerCase())) {
                tokens.add(new Token(s, Token.Type.ACTIVITY));
                continue;
            }

            if (KeywordList.TimeList.contains(s.trim().toLowerCase()) || s.contains("/") || s.contains(":")) {
                tokens.add(new Token(s, Token.Type.TIME));
                continue;
            }

            if (s.contains("@")) {
                tokens.add(new Token(s, Token.Type.NAME));
            } else {
                tokens.add(new Token(s, Token.Type.TITLE));
            }
        }
        return tokens;
    }

    /**
     *
     * @return If there are any remaining tokens for the parser to consider
     */
    public boolean hasNext() {
        return iteratorindex < tokenised.size();
    }

    /**
     *
     * @return The next token, be sure to call toNext() to go to next
     */
    public Token getNext() {
        return tokenised.get(iteratorindex);
    }

    /**
     * Goes to next token index (if exists)
     */
    public void toNext() {
        iteratorindex++;
    }

}
