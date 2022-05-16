package com.ajsmdllz.fitomatic.Search;

import java.util.ArrayList;

public class SearchTokenizer {
    private String search;
    private String[] searchElems;


    public SearchTokenizer(String input) {
        this.search = input;
        this.searchElems = input.split("[ ,]+");
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

}
