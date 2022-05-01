package com.ajsmdllz.fitomatic.Search;

public class Token {
    // The following enum defines different types of tokens. Example of accessing these: Token.Type.INT
    public enum Type {USER, ACTIVITY}


    // Fields of the class Token.
    private final String token; // Token representation in String form.
    private final Type type;    // Type of the token.

    public Token(String token, Type type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true; // Same hashcode.
        if (!(other instanceof Token)) return false; // Null or not the same type.
        return this.type == ((Token) other).getType() && this.token.equals(((Token) other).getToken()); // Values are the same.
    }

    @Override
    public String toString() {
        return type + "("+ token+ ")";
    }
}
