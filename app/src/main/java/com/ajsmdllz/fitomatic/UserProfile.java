package com.ajsmdllz.fitomatic;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Creating a test User to display their info
        User testUser = new User("Bob", "password", 42, "Male");
        testUser.setUserName("Jeff");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        TextView tvProfileTitle = findViewById(R.id.profiletitle);
        tvProfileTitle.setText("Name: "+testUser.getUserName() + ", Age: "+testUser.getUserAge());
    }

    // Testing how we might edit our profile
    public void editProfile (View v) {
        TextView tvProfileTitle = findViewById(R.id.profiletitle);
        tvProfileTitle.setText("Entered Edit Mode");
    }
}