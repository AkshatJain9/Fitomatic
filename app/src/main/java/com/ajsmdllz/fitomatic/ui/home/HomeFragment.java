package com.ajsmdllz.fitomatic.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ajsmdllz.fitomatic.FeedAdapter;
import com.ajsmdllz.fitomatic.LoginSuccess;
import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.Search.SimpleTokenizer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Search Bar
        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
        SearchView tvSearchBar = getView().findViewById(R.id.searchBar);
        tvSearchBar.setQueryHint("Search for something");

        tvSearchBar.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        // Pass into tokenizer
        tvSearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SimpleTokenizer tokens = new SimpleTokenizer(query);
                Toast.makeText(getContext().getApplicationContext(), tokens.getOutputTokens(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        // Home Button (sends user to home/main page)

        // Feed
        ListView feed = getView().findViewById(R.id.feed);
        ArrayList<FirebaseUser> users = new ArrayList<>();

        FeedAdapter feedAdapter = new FeedAdapter(getContext(),R.layout.login_success_list_user,users);

        // Adding yourself to list for now
        users.add(mAuth.getCurrentUser());
        feedAdapter.add(mAuth.getCurrentUser());

        feed.setAdapter(feedAdapter);
        // End of Feed Code
    }

}