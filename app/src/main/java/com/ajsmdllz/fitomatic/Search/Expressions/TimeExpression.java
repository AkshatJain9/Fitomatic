package com.ajsmdllz.fitomatic.Search.Expressions;


import androidx.annotation.NonNull;

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

    @NonNull
    @Override
    public String toString() {
        return "TimeExpression{" +
                "time='" + time + '\'' +
                ", next=" + next +
                '}';
    }
}
