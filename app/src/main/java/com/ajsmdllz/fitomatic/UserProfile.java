package com.ajsmdllz.fitomatic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class UserProfile extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Display User information
        TextView tvProfileTitle = findViewById(R.id.profiletitle);
        tvProfileTitle.setText("Email: "+mAuth.getCurrentUser().getEmail());

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
        TextView tvProfileTitle = findViewById(R.id.profiletitle);
        tvProfileTitle.setText("Entered Edit Mode");
    }

    public void logout(View v) {
        mAuth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}