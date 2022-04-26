package com.ajsmdllz.fitomatic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        Button createProfile = findViewById(R.id.createProfile);
        EditText name = findViewById(R.id.firstName);
        EditText bio = findViewById(R.id.bio);
        SeekBar seek = findViewById(R.id.seekBar);
        TextView age = (TextView) findViewById(R.id.textView2);

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("")) {
                    Toast.makeText(ProfileCreation.this, "Name required!", Toast.LENGTH_SHORT).show();
                } else {
                    // Creates new User
                    // NOTE: Does not do anything yet (needs to store info in firebase)
//                    User user = new User(name.getText().toString(), 0, "Gender", bio.getText().toString());
                    startActivity(new Intent(ProfileCreation.this, LoginSuccess.class));
                }
            }
        });

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                System.out.println(i);
                age.setText("Age "+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}