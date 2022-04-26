package com.ajsmdllz.fitomatic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class ChooseInterests extends AppCompatActivity {
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_interests);
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");


        // Next Step button
        Button next = findViewById(R.id.nextStep);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChipGroup interestClicked = findViewById(R.id.allInterests);
                ArrayList<String> interestsToAdd = new ArrayList<>();
                for (int i = 0; i < interestClicked.getChildCount(); i++) {
                    if (((Chip) interestClicked.getChildAt(i)).isChecked()) { // To find appropriate function
                        interestsToAdd.add(((Chip) interestClicked.getChildAt(i)).getText().toString());
                    }
                }

                db.collection("users").document(email).update("interests", interestsToAdd);

                Intent toDetails = new Intent(ChooseInterests.this, ProfileCreation.class);
                toDetails.putExtra("email", email);
                startActivity(toDetails);
            }
        });
    }
}