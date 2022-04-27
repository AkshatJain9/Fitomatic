package com.ajsmdllz.fitomatic;

import java.util.Scanner;

public class SimpleTokenizer extends Tokenizer {
    private String text;    // save text
    private int pos;        // current position
    private Object current; // save token extracted

    public SimpleTokenizer(String input) {
        this.text = input.trim();
    }

    public static void main(String[] args) {
        // Create a scanner to get the user's input.
        Scanner scanner = new Scanner(System.in);

        /*
         Continue to get the user's input until they exit.
         To exit press: Control + D or providing the string 'q'
         Example input you can try: ((1 + 2) * 5)/2
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
