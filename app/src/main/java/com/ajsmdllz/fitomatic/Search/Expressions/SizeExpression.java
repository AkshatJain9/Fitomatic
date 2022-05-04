package com.ajsmdllz.fitomatic.Search.Expressions;

public class SizeExpression extends Exp {
    String size;
    Exp next;

    public SizeExpression (String s, Exp n) {
        this.size = s;
        this.next = n;
    }


    @Override
    public String show() {
        return "SIZE";
    }

    @Override
    public String getVal() {
        return size;
    }

    @Override
    public Exp getNext() {
        return next;
    }
}
