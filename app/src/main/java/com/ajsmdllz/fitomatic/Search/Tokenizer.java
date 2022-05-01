package com.ajsmdllz.fitomatic.Search;

public abstract class Tokenizer {

    // extract next token from the current text and save it
    public abstract void next();

    // return the current token (without type information)
    public abstract Object current();

    //check whether there is a token remaining in the text
    public abstract boolean hasNext();
}
