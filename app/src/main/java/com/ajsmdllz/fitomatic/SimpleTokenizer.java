package com.ajsmdllz.fitomatic;

public class SimpleTokenizer extends Tokenizer {
    private String text;    // save text
    private int pos;        // current position
    private Object current; // save token extracted

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void next() {

    }

    @Override
    public Object current() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }
}
