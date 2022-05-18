package com.ajsmdllz.fitomatic.Search;

import java.util.ArrayList;

public class SearchTokenizer {
    private final String[] searchElems;
    private final ArrayList<Token> tokenised;
    private int iteratorIndex = 0;


    public SearchTokenizer(String input) {
        this.searchElems = input.split("[ ,]+"); // Regex for removing all whitespace + ","
        this.tokenised = tokenize();
    }

    /**
     * Given the input search string in array form, segment them into a sequential Token Array
     * @return ArrayList of Tokens
     */
    public ArrayList<Token> tokenize() {
        ArrayList<Token> tokens = new ArrayList<>();
        // Tokenizing Each String
        for (String s : searchElems) {
            // Any format of Activity String is Identified
            if (KeywordList.ActivityList.contains(s.trim().toLowerCase())) {
                tokens.add(new Token(s, Token.Type.ACTIVITY));
                continue;
            }
            // Any common format of Time is identified
            if (KeywordList.TimeList.contains(s.trim().toLowerCase()) || s.contains("/") || s.contains(":")) {
                tokens.add(new Token(s, Token.Type.TIME));
                continue;
            }
            // Users must be identified with an "@", all other strings treated as titles
            if (s.contains("@")) {
                tokens.add(new Token(s, Token.Type.NAME));
            } else {
                tokens.add(new Token(s, Token.Type.TITLE));
            }
        }
        return tokens;
    }

    /**
     * @return If there are any remaining tokens for the parser to consider
     */
    public boolean isEnd() {
        return iteratorIndex >= tokenised.size();
    }

    /**
     *
     * @return The next token, be sure to call toNext() to go to next
     */
    public Token getNext() {
        return tokenised.get(iteratorIndex);
    }

    /**
     * Goes to next token index (if exists)
     */
    public void toNext() {
        iteratorIndex++;
    }

}
