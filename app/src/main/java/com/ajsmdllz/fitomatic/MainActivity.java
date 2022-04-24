package com.ajsmdllz.fitomatic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ajsmdllz.fitomatic.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(MainActivity.this, LoginSuccess.class));
        }
    }

    public void toRegistration(View v) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

    public void loginUser(View v) {
        EditText email = findViewById(R.id.name);
        EditText password = findViewById(R.id.pass);
        if (email.getText().toString().length() == 0) {
            Toast.makeText(MainActivity.this, "Please enter an email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.getText().toString().length() == 0) {
            Toast.makeText(MainActivity.this, "Please enter an password!", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    startActivity(new Intent(MainActivity.this, LoginSuccess.class));
                } else {
                    Toast.makeText(MainActivity.this, "Account not found. Please check Email and Password!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}