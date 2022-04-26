package com.ajsmdllz.fitomatic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileCreation extends AppCompatActivity {
    FirebaseFirestore db;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);
        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
    }

    public void createAccount(View v) {
        EditText fname = findViewById(R.id.firstName);
        EditText lname = findViewById(R.id.lastName);
        EditText bio = findViewById(R.id.bio);
        RadioGroup genders = findViewById(R.id.radioGroup);
        int selected = genders.getCheckedRadioButtonId();

        if (selected == - 1) {
            Toast.makeText(ProfileCreation.this, "Please enter your Gender!", Toast.LENGTH_SHORT).show();
            return;
        }
        System.out.println(selected);
        RadioButton temp = findViewById(selected);
        String gender = temp.getText().toString();

        if (fname.getText().toString().length() == 0) {
            Toast.makeText(ProfileCreation.this, "Please enter your First Name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (lname.getText().toString().length() == 0) {
            Toast.makeText(ProfileCreation.this, "Please enter your Last Name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (bio.getText().toString().length() == 0) {
            Toast.makeText(ProfileCreation.this, "Please enter a Bio!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (gender.length() == 0) {
            Toast.makeText(ProfileCreation.this, "Please enter your Gender!", Toast.LENGTH_SHORT).show();
        } else {
            SeekBar seekBar = (SeekBar) findViewById(R.id.ageBar);
            TextView ageView = (TextView) findViewById(R.id.ageView);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    ageView.setText("Age " + String.valueOf(i));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
            db.collection("users").document(email).update("firstname", fname.getText().toString());
            db.collection("users").document(email).update("lastname", lname.getText().toString());
            db.collection("users").document(email).update("bio", bio.getText().toString());
            db.collection("users").document(email).update("gender", gender);
            db.collection("users").document(email).update("age", seekBar.getProgress());

            Toast.makeText(ProfileCreation.this, "Your Profile has been Created!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ProfileCreation.this, LoginSuccess.class));

        }

    }
}