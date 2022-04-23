package com.ajsmdllz.fitomatic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class LoginSuccess extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        mAuth = FirebaseAuth.getInstance();
        // Search Bar
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView tvSearchBar = findViewById(R.id.searchBar);
        tvSearchBar.setQueryHint("Search for something");

        tvSearchBar.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        // End of Search Bar Code

        // Feed
        ListView feed = findViewById(R.id.feed);
        ArrayList<User> feedArrayList = new ArrayList<>();

        // Adding test Users to display on feed
        User testUser1 = new User("Barry", 35, "Male");
        feedArrayList.add(testUser1);
        User testUser2 = new User("Claire", 26, "Female");
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);
        feedArrayList.add(testUser2);

        ArrayAdapter<User> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, feedArrayList);
        feed.setAdapter(adapter);
        // End of Feed Code

    }

    public void viewProfile(View v) {
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
    }
}