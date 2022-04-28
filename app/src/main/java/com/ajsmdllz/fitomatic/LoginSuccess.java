package com.ajsmdllz.fitomatic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class LoginSuccess extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        // Search Bar
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView tvSearchBar = findViewById(R.id.searchBar);
        tvSearchBar.setQueryHint("Search for something");

        tvSearchBar.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        // Pass into tokenizer
        tvSearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SimpleTokenizer tokens = new SimpleTokenizer(query);
                Toast.makeText(getApplicationContext(), tokens.getOutputTokens(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

            // End of Search Bar Code

        // Feed
        ListView feed = findViewById(R.id.feed);
        ArrayList<FirebaseUser> users = new ArrayList<>();

        FeedAdapter feedAdapter = new FeedAdapter(LoginSuccess.this,R.layout.login_success_list_user,users);

        // Adding yourself to list for now
        users.add(mAuth.getCurrentUser());
        feedAdapter.add(mAuth.getCurrentUser());

        feed.setAdapter(feedAdapter);
        // End of Feed Code

        // Home Button (sends user to home/main page)
        ImageButton homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginSuccess.this, LoginSuccess.class));
            }
        });

        // Profile Button (sends user to their profile page)
        ImageButton profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginSuccess.this, UserProfile.class));
            }
        });

        // Create Post Button (sends user to post creation page)
        ImageButton createPostButton = findViewById(R.id.createPostButton);
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginSuccess.this, PostCreationChoice.class));
            }
        });

    }
}