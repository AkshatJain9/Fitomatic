package com.ajsmdllz.fitomatic.Search;

import com.ajsmdllz.fitomatic.Search.Expressions.*;


import java.util.ArrayList;

public class SearchParser {
    SearchTokenizer tokenizer;
    ArrayList<Token> tokens;
    int index;

    public SearchParser (SearchTokenizer st) {
        this.tokenizer = st;
        this.tokens = st.tokenize();
        index = 0;
    }


    public Exp parseStatement() {
        if (tokens.size() == 0) {
            return new EmptyExpression();
        }

        if (tokens.get(0).getType() == Token.Type.NAME) {
            index++;
            return new UserQueryExpression(tokens.get(0).getToken(), parseFields());
        }

        return parseFields();
    }

    public Exp parseFields() {
        if (index >= tokens.size()) {
            return new EmptyExpression();
        }

        if (tokens.get(index).getType() == Token.Type.EVENTDESC) {
            index++;
            return new PostQueryExpression(tokens.get(index - 1).getToken(), parseFields());
        }

        return parseActivites();

    }

    public Exp parseActivites() {
        if (index >= tokens.size()) {
            return new EmptyExpression();
        }

        if (tokens.get(index).getType() == Token.Type.ACTIVITY) {
            index++;
            return new ActivityQueryExpression(tokens.get(index - 1).getToken(), parseActivites());
        }

        return parseTime();
    }

    public Exp parseTime() {
        if (index >= tokens.size()) {
            return new EmptyExpression();
        }
        if (tokens.get(index).getType() == Token.Type.TIME) {
            index++;
            return new TimeExpression(tokens.get(index - 1).getToken(), parseSize());
        }
        return parseSize();
    }

    public Exp parseSize() {
        if (index >= tokens.size()) {
            return new EmptyExpression();
        }

        return new SizeExpression(tokens.get(index).getToken(), new EmptyExpression());
    }



}
