package com.ajsmdllz.fitomatic;

import com.google.firebase.auth.FirebaseAuth;

public class Email {
    private static FirebaseAuth mAuth;
    private static Email instance = null;

    public static Email getInstance() {
        mAuth = FirebaseAuth.getInstance();
        if (instance == null) {
            mAuth.getCurrentUser().getEmail();
        } else {
            System.out.printf("Instance has already been created!!!");
        }
        return instance;
    }
}
