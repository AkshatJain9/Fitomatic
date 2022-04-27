package com.ajsmdllz.fitomatic;

import java.util.Scanner;

public class SimpleTokenizer extends Tokenizer {
    private String text;        // save text
    private Token currentToken; // save token extracted

    public SimpleTokenizer(String input) {
        this.text = input.trim();
        next();
    }

    public static void main(String[] args) {
        // Create a scanner to get the user's input.
        Scanner scanner = new Scanner(System.in);

        /*
         Continue to get the user's input until they exit.
         To exit press: Control + D or providing the string 'q'
         */
        System.out.println("Provide a test string to be tokenized:");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            // Check if 'quit' is provided.
            if (input.equals("q"))
                break;

            // Create an instance of the tokenizer.
            Tokenizer tokenizer = new SimpleTokenizer(input);

            // Print all the tokens.
            while (tokenizer.hasNext()) {
                System.out.print(tokenizer.current() + " ");
                tokenizer.next();
            }
            System.out.println();
        }
    }


    @Override
    public void next() {
        // Remove any whitespace
        String buffer = this.text.trim();
        if (buffer.isEmpty()) {
            this.currentToken = null;
            return;
        }

        // If it starts with a Capital Letter Token is a name ("USER")
        if (text.charAt(0) >= 'A' && text.charAt(0) <= 'Z') {
            int pos = 0;
            while (pos < text.length()) {
                if (text.charAt(pos) == ' ') break;
                pos++;
            }
            this.currentToken = new Token(text.substring(0,pos), Token.Type.USER);
        } else { // its an activity
            int pos = 0;
            while (pos < text.length()) {
                if (text.charAt(pos) == ' ') break;
                pos++;
            }
            this.currentToken = new Token(text.substring(0,pos), Token.Type.ACTIVITY);
        }
        this.text = buffer.substring(currentToken.getToken().length());
        this.text = text.trim();
    }

    @Override
    public Token current() {
        return this.currentToken;
    }

    @Override
    public boolean hasNext() {
        if (this.currentToken == null) return false;
        return true;
    }
}
