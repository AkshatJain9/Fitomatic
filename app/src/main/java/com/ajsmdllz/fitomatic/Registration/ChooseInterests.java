package com.ajsmdllz.fitomatic.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ajsmdllz.fitomatic.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ChooseInterests extends AppCompatActivity {
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_interests);
        db = FirebaseFirestore.getInstance();
        // Retrieving Email from Previous Activity to continue Indexing
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        // Next Step Button Listener
        Button next = findViewById(R.id.nextStep);
        next.setOnClickListener(view -> {
            ChipGroup interestClicked = findViewById(R.id.allInterests);
            ArrayList<String> interestsToAdd = new ArrayList<>();
            // Find all clicked Chips, and add to interests attribute of the specified User
            for (int i = 0; i < interestClicked.getChildCount(); i++) {
                if (((Chip) interestClicked.getChildAt(i)).isChecked()) { // To find appropriate function
                    interestsToAdd.add(((Chip) interestClicked.getChildAt(i)).getText().toString());
                }
            }
            db.collection("users").document(email).update("interests", interestsToAdd);
            // Send email to next activity to continue registration
            Intent toDetails = new Intent(ChooseInterests.this, ProfileCreation.class);
            toDetails.putExtra("email", email);
            startActivity(toDetails);
        });
    }
}