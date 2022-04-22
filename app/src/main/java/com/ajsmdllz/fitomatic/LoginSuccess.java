package com.ajsmdllz.fitomatic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

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
        //tvSearchBar.setIconified(false);
    }

    public void viewProfile(View v) {
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
    }

    public void logout(View v) {
        mAuth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}