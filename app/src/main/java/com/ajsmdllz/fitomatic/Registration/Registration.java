package com.ajsmdllz.fitomatic.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ajsmdllz.fitomatic.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

public class Registration extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_fire_base);
    }

    public void backButton(View v) {
        finish();
    }

    /**
     * Checks fields and ensures only valid user objects are made and stored in the db
     */
    public void RegisterUser(View v) {
        EditText email = findViewById(R.id.emailreg);
        EditText pass = findViewById(R.id.passreg);
        EditText passConfirm = findViewById(R.id.passregc);
        if (email.getText().toString().length() == 0) {
            Toast.makeText(this, "Please enter an email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.getText().toString().length() == 0) {
            Toast.makeText(this, "Please enter a password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (passConfirm.getText().toString().length() == 0) {
            Toast.makeText(this, "Please confirm your password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pass.getText().toString().equals(passConfirm.getText().toString())) {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }
        DocumentReference doc = db.collection("users").document(email.getText().toString().toLowerCase(Locale.ROOT));
        // If input fields are valid, check if user has already been created, if not call createAccount()
        doc.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot fullDoc = task.getResult();
                if (!fullDoc.exists()) {
                    createAccount(email.getText().toString().toLowerCase(Locale.ROOT), pass.getText().toString());
                } else {
                    Toast.makeText(Registration.this, "Account with this email already exists!", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(Registration.this, "Something went wrong (Please let AJ know if you somehow get this error)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Creates the user in Authentication Database, and gives feedback to the user
     * @param email Email (PK) of the user being made
     * @param password Password of User
     */
    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        User user = new User(email);
                        db.collection("users").document(email).set(user);
                        // Feedback to user
                        Toast.makeText(Registration.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                        Intent toProfileCreation = new Intent(Registration.this, ChooseInterests.class);
                        toProfileCreation.putExtra("email", email);
                        startActivity(toProfileCreation);

                        Toast.makeText(Registration.this, "Authentication SUCCESS.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Registration.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}