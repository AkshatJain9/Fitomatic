package com.ajsmdllz.fitomatic.Search.Expressions;

import androidx.annotation.NonNull;

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

    @NonNull
    @Override
    public String toString() {
        return "EmptyExpression{}";
    }
}
