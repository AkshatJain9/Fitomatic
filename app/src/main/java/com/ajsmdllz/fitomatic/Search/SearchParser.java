package com.ajsmdllz.fitomatic.Search;

import com.ajsmdllz.fitomatic.Search.Expressions.*;
import java.util.ArrayList;

public class SearchParser {
    SearchTokenizer tokenizer;
    public ArrayList<Token> tokens;
    int index;

    public SearchParser (SearchTokenizer st) {
        this.tokenizer = st;
        this.tokens = st.tokenize();
        index = 0;
    }

    /**
     * A Statement can either be Parsed to be just a User Expression or Just search fields
     * @return <Statement> => userExp(<Fields>) | <Fields>
     */
    public Exp parseStatement() {
        if (tokens.size() == 0) {
            return new EmptyExpression();
        }

        if (tokens.get(0).getType() == Token.Type.NAME) {
            index++;
            return new UserQueryExpression(tokens.get(0).getToken(), parseFields());
        }

        return parseActivites();
    }

    /**
     * Activities refer to any physical activity
     * @return <Activity> => ActivityQueryExp(<Activity>) | <Time>
     */
    public Exp parseActivites() {
        if (index >= tokens.size()) {
            return new EmptyExpression();
        }

        if (tokens.get(index).getType() == Token.Type.ACTIVITY) {
            index++;
            String s = tokens.get(index - 1).getToken().toLowerCase();
            String input = s.substring(0,1).toUpperCase() + s.substring(1);
            return new ActivityQueryExpression(input, parseActivites());
        }

        return parseFields();
    }

    /**
     * A Field refers to any attributes that pertain to the description or title of a post
     * @return <Field> => PostQueryExp(<Fields>) | <Activities>
     */
    public Exp parseFields() {
        if (index >= tokens.size()) {
            return new EmptyExpression();
        }

        if (tokens.get(index).getType() == Token.Type.TITLE) {
            index++;
            return new PostQueryExpression(tokens.get(index - 1).getToken(), parseTime());
        }

        return parseTime();

    }

    /**
     * Time refers to any date-related query
     * @return <Time> => TimeExp(<Size>) | <Empty>
     */
    public Exp parseTime() {
        if (index >= tokens.size()) {
            return new EmptyExpression();
        }
        if (tokens.get(index).getType() == Token.Type.TIME) {
            index++;
            return new TimeExpression(tokens.get(index - 1).getToken(), parseTime());
        }
        return new EmptyExpression();
    }

//    /**
//     * Size refers to the scale of the event being searched
//     * @return <Size> => SizeExp(EmptyExp) | EmptyExp
//     */
//    public Exp parseSize() {
//        if (index >= tokens.size()) {
//            return new EmptyExpression();
//        }
//        return new SizeExpression(tokens.get(index).getToken(), new EmptyExpression());
//    }
}
