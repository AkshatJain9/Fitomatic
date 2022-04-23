package com.ajsmdllz.fitomatic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        Button createProfile = findViewById(R.id.createProfile);
        EditText name = findViewById(R.id.name);
        EditText bio = findViewById(R.id.bio);

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("")) {
                    Toast.makeText(ProfileCreation.this, "Name required!", Toast.LENGTH_SHORT).show();
                } else {
                    // Creates new User
                    // NOTE: Does not do anything yet (needs to store info in firebase)
                    User user = new User(name.getText().toString(), 0, "Gender", bio.getText().toString());
                    startActivity(new Intent(ProfileCreation.this, LoginSuccess.class));
                }
            }
        });

    }
}