package com.ajsmdllz.fitomatic.Search;

import com.ajsmdllz.fitomatic.Search.Expressions.*;
import java.util.ArrayList;

public class SearchParser {
    SearchTokenizer tokenizer;

    public SearchParser (SearchTokenizer st) {
        this.tokenizer = st;
    }

    /**
     * A Statement can either be Parsed to be just a User Expression or Just search fields
     * @return <Statement> => userExp(<Fields>) | <Fields>
     */
    public Exp parseStatement() {
        if (tokenizer.getNext().getType() == Token.Type.NAME) {
            String user = tokenizer.getNext().getToken();
            tokenizer.toNext();
            return new UserQueryExpression(user, parseActivities());
        }

        return parseActivities();
    }

    /**
     * Activities refer to any physical activity
     * @return <Activity> => ActivityQueryExp(<Activity>) | <Time>
     */
    public Exp parseActivities() {
        if (!tokenizer.hasNext()) {
            return new EmptyExpression();
        }

        if (tokenizer.getNext().getType() == Token.Type.ACTIVITY) {
            String s = tokenizer.getNext().getToken().toLowerCase();
            String input = s.substring(0,1).toUpperCase() + s.substring(1);
            tokenizer.toNext();
            return new ActivityQueryExpression(input, parseActivities());
        }



//        if (index >= tokens.size()) {
//            return new EmptyExpression();
//        }
//
//        if (tokens.get(index).getType() == Token.Type.ACTIVITY) {
//            index++;
//            String s = tokens.get(index - 1).getToken().toLowerCase();
//            String input = s.substring(0,1).toUpperCase() + s.substring(1);
//            return new ActivityQueryExpression(input, parseActivites());
//        }

        return parseFields();
    }

    /**
     * A Field refers to any attributes that pertain to the description or title of a post
     * @return <Field> => PostQueryExp(<Fields>) | <Activities>
     */
    public Exp parseFields() {
        if (!tokenizer.hasNext()) {
            return new EmptyExpression();
        }

        if (tokenizer.getNext().getType() == Token.Type.TITLE) {
            String field = tokenizer.getNext().getToken();
            tokenizer.toNext();
            return new PostQueryExpression(field, parseFields());
        }
//        if (index >= tokens.size()) {
//            return new EmptyExpression();
//        }
//
//        if (tokens.get(index).getType() == Token.Type.TITLE) {
//            index++;
//            return new PostQueryExpression(tokens.get(index - 1).getToken(), parseFields());
//        }

        return parseTime();

    }

    /**
     * Time refers to any date-related query
     * @return <Time> => TimeExp(<Size>) | <Empty>
     */
    public Exp parseTime() {
        if (!tokenizer.hasNext()) {
            return new EmptyExpression();
        }
        if (tokenizer.getNext().getType() == Token.Type.TIME) {
            String time = tokenizer.getNext().getToken();
            tokenizer.toNext();
            return new TimeExpression(time, new EmptyExpression());
        }
//        if (index >= tokens.size()) {
//            return new EmptyExpression();
//        }
//        if (tokens.get(index).getType() == Token.Type.TIME) {
//            index++;
//            return new TimeExpression(tokens.get(index - 1).getToken(), parseTime());
//        }
        return new EmptyExpression();
    }


}
