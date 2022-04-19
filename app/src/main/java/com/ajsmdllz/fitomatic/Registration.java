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

public class Registration extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_fire_base);

    }

    public void backButton(View v) {
        finish();
    }

    public void RegisterUser(View v) {
          EditText email = findViewById(R.id.emailreg);
          EditText pass = findViewById(R.id.passreg);
          EditText passConfirm = findViewById(R.id.passregc);
          if (pass.getText().equals(passConfirm.getText())) {
              createAccount(email.getText().toString(), pass.getText().toString());
          } else {
              Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
          }
    }

    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("EmailPassword", "createUserWithEmail:success");
                            Toast.makeText(Registration.this, "Authentication SUCCESS.",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("EmailPassword", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Toast.makeText(Registration.this, "Failed to register: "+task.getException().getMessage()+"!", Toast.LENGTH_SHORT).show();
                            Log.e("Firebase", "Failed to register", task.getException());
                        }
                    }
                });
        // [END create_user_with_email]
    }

}