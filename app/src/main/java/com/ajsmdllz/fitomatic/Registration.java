package com.ajsmdllz.fitomatic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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
        DocumentReference doc = db.collection("users").document(email.getText().toString());
        doc.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot fullDoc = task.getResult();
                if (!fullDoc.exists()) {
                    createAccount(email.getText().toString(), pass.getText().toString());
                } else {
                    Toast.makeText(Registration.this, "Account with this email already exists!", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(Registration.this, "Something went wrong (Please let AJ know if you somehow get this error)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        User user = new User(email);
                        db.collection("users").document(email).set(user);

                        Toast.makeText(Registration.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                        Intent toProfileCreation = new Intent(Registration.this, ChooseInterests.class);
                        toProfileCreation.putExtra("email", email.toString());
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