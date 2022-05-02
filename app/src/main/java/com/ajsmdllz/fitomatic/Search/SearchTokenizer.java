package com.ajsmdllz.fitomatic.Search;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchTokenizer {
    private String search;
    private String[] searchElems;


    public SearchTokenizer(String input) {
        this.search = input;
        this.searchElems = input.split("[ ,]+");
    }

    public ArrayList<Token> tokenize() {
        ArrayList<Token> tokens = new ArrayList<>();
        for (String s : searchElems) {
            if (ActivityList.ActivityList.contains(s)) {
                tokens.add(new Token(s, Token.Type.ACTIVITY));
                continue;
            }
            if (s.equals("small") || s.equals("one-on-one") || s.equals("large") || Character.isDigit(s.charAt(0))) {
                tokens.add(new Token(s, Token.Type.SIZE));
                continue;
            }
            if (s.charAt(0) >= 'A' && s.charAt(0) <= 'Z') {
                tokens.add(new Token(s, Token.Type.NAME));
            } else {
                tokens.add(new Token(s, Token.Type.EVENTDESC));
            }
        }
        return tokens;
    }

}
