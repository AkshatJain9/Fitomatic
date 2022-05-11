package com.ajsmdllz.fitomatic.Search.Expressions;

public class UserQueryExpression extends Exp {
    public String user;
    Exp fields;


    public UserQueryExpression (String user, Exp f) {
        this.user = user;
        this.fields = f;
    }

    @Override
    public String show() {
        return "USER";
    }

    @Override
    public String getVal() {
        return user;
    }

    @Override
    public Exp getNext() {
        return fields;
    }

    @Override
    public String toString() {
        return "UserQueryExpression{" +
                "user='" + user + '\'' +
                ", fields=" + fields +
                '}';
    }
}
