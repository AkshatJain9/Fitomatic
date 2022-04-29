package com.ajsmdllz.fitomatic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.firestore.FirebaseFirestore;

public class PostCreationChoice extends AppCompatActivity {
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_creation_choice);
        db = FirebaseFirestore.getInstance();

        Button createSinglePost = findViewById(R.id.createSingleButton);
        Button createGroupPost = findViewById(R.id.createGroupButton);

        // createSinglePost Button handler
        createSinglePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostCreationChoice.this, PostCreationSingle.class));
            }
        });

        // createGroupPost Button handler
        createGroupPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostCreationChoice.this, PostCreationGroup.class));
            }
        });

        // Home Button (sends user to home/main page)
        ImageButton homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostCreationChoice.this, LoginSuccess.class));
            }
        });

        // Profile Button (sends user to their profile page)
        ImageButton profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostCreationChoice.this, UserProfile.class));
            }
        });

        // Create Post Button (sends user to post creation page)
        ImageButton createPostButton = findViewById(R.id.createPostButton);
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostCreationChoice.this, PostCreationChoice.class));
            }
        });
    }
}