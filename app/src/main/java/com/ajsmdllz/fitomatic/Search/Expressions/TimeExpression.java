package com.ajsmdllz.fitomatic.Search.Expressions;


public class TimeExpression extends Exp {
    String time;
    Exp next;

    public TimeExpression (String t, Exp n) {
        this.time = t;
        this.next = n;
    }


    @Override
    public String show() {
        return "TIME";
    }

    @Override
    public String getVal() {
        return time;
    }

    @Override
    public Exp getNext() {
        return next;
    }
}
