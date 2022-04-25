package com.ajsmdllz.fitomatic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PostCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_creation);

        Button createPost = findViewById(R.id.createPost);
        EditText title = findViewById(R.id.createTitle);

        // createPost Button handler
        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // needs checks here to see whether the post being created is valid or not
            }
        });

        // Home Button (sends user to home/main page)
        ImageButton homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostCreation.this, LoginSuccess.class));
            }
        });

        // Profile Button (sends user to their profile page)
        ImageButton profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostCreation.this, UserProfile.class));
            }
        });

        // Create Post Button (sends user to post creation page)
        ImageButton createPostButton = findViewById(R.id.createPostButton);
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostCreation.this, PostCreation.class));
            }
        });
    }
}