package com.ajsmdllz.fitomatic.Search.Expressions;

public class EmptyExpression extends Exp {
    @Override
    public String show() {
        return "EMPTY";
    }

    @Override
    public String getVal() {
        return null;
    }

    @Override
    public Exp getNext() {
        return null;
    }

    @Override
    public String toString() {
        return "EmptyExpression{}";
    }
}
