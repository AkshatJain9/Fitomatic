package com.ajsmdllz.fitomatic.Search.Expressions;

public class PostQueryExpression extends Exp {
    String attribute;
    Exp furtherAttributes;

    public PostQueryExpression (String s, Exp e) {
        this.attribute = s;
        this.furtherAttributes = e;
    }

    @Override
    public String show() {
        return "POST";
    }

    @Override
    public String getVal() {
        return attribute;
    }

    @Override
    public Exp getNext() {
        return furtherAttributes;
    }

    @Override
    public String toString() {
        return "PostQueryExpression{" +
                "attribute='" + attribute + '\'' +
                ", furtherAttributes=" + furtherAttributes +
                '}';
    }
}
