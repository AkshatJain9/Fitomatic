package com.ajsmdllz.fitomatic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class PostCreationSingle extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String selectedActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_creation_single);

        // Dropdown of activities
        Spinner activity = findViewById(R.id.spinnerActivitySingle);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(PostCreationSingle.this, R.array.activities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        activity.setAdapter(adapter);
        // Sets listener for the spinner
        activity.setOnItemSelectedListener(this);

        EditText title = findViewById(R.id.createTitleSingle);
        EditText bio = findViewById(R.id.createDescriptionSingle);

        // Create Post Button
        Button createPost = findViewById(R.id.createPostSingle);
        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This is where checks will be needed and then creating the post will happen
                // FIXME -- Need to implement firebase Post stuff here (not sure how to do image handling)
            }
        });

        // Home Button (simply sends user back to home/main page)
        ImageButton homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostCreationSingle.this, LoginSuccess.class));
            }
        });

        // Profile Button (sends user to their profile page)
        ImageButton profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostCreationSingle.this, UserProfile.class));
            }
        });

        // Create Post Button (sends user to post creation page)
        ImageButton createPostButton = findViewById(R.id.createPostButton);
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostCreationSingle.this, PostCreationChoice.class));
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String selectedActivity = (String) parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}