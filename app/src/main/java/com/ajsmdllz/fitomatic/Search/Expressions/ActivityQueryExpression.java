package com.ajsmdllz.fitomatic.Search.Expressions;

public class ActivityQueryExpression extends Exp{

    String activity;
    Exp next;

    public ActivityQueryExpression (String a, Exp n) {
        this.activity = a;
        this.next = n;
    }


    @Override
    public String show() {
        return "ACTIVITY";
    }

    @Override
    public String getVal() {
        return activity;
    }

    @Override
    public Exp getNext() {
        return next;
    }
}
