package com.ajsmdllz.fitomatic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

public class UserProfile extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    String email;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Getting user's info from Firebase
        db.collection("users").document(mAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                String firstName = task.getResult().getString("firstname");
                String lastName = task.getResult().getString("lastname");
                String bio = task.getResult().getString("bio");
                String gender = task.getResult().getString("gender");
                int age = task.getResult().getDouble("age").intValue();

                // Display User information
                TextView tvProfileTitle = findViewById(R.id.profileName);
                tvProfileTitle.setText("Displaying basic user info:\n " +
                        "Email: "+mAuth.getCurrentUser().getEmail()+"\n " +
                        "Full Name: "+firstName+" "+lastName+"\n" +
                        "bio: "+bio+"\n" +
                        "gender: "+gender+"\n" +
                        "age: "+age);

            } else {
                Toast.makeText(this, "(Something went wrong!) Should not get here - DENI", Toast.LENGTH_SHORT).show();
            }
        });


        // Home Button (simply sends user back to home/main page)
        ImageButton homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfile.this, LoginSuccess.class));
            }
        });

        // Profile Button (sends user to their profile page)
        ImageButton profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfile.this, UserProfile.class));
            }
        });

        // Create Post Button (sends user to post creation page)
        ImageButton createPostButton = findViewById(R.id.createPostButton);
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfile.this, PostCreation.class));
            }
        });
    }

    // Testing how we might edit our profile
    public void editProfile (View v) {
        TextView tvProfileTitle = findViewById(R.id.profileName);
        tvProfileTitle.setText("Entered Edit Mode");
    }

    public void logout(View v) {
        mAuth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}