package com.ajsmdllz.fitomatic.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajsmdllz.fitomatic.AVLPosts;
import com.ajsmdllz.fitomatic.Posts.Post;
import com.ajsmdllz.fitomatic.Posts.PostFactory;
import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.RecycleFeedAdapter;
import com.ajsmdllz.fitomatic.Search.DBQuery;
import com.ajsmdllz.fitomatic.Search.Expressions.Exp;
import com.ajsmdllz.fitomatic.Search.SearchParser;
import com.ajsmdllz.fitomatic.Search.SearchTokenizer;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FirebaseFirestore db;
    RecyclerView feed;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set up Search Bar
        SearchManager searchManager = (SearchManager) requireContext().getSystemService(Context.SEARCH_SERVICE);
        SearchView tvSearchBar = requireView().findViewById(R.id.searchBar);
        tvSearchBar.setQueryHint("Search for something");

        tvSearchBar.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));

        // Pass into tokenizer
        tvSearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // When string submitted, parse into Expression
                SearchTokenizer tokens = new SearchTokenizer(query);
                SearchParser parser = new SearchParser(tokens);
                Exp e = parser.parseStatement();
                // Store Searched Posts for Display
                ArrayList<Post> searchedPosts = new ArrayList<>();
                // Parse into query object to be called
                DBQuery queryHandler = DBQuery.getInstance();
                CollectionReference colRef = db.collection("posts");
                Query postQuery = queryHandler.getQuery(e, colRef);
                postQuery.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        ArrayList<DocumentSnapshot> docs = (ArrayList<DocumentSnapshot>) snapshot.getDocuments();
                        PostFactory fact = new PostFactory();
                        for (DocumentSnapshot d : docs) {
                            // Uses PostFactory to create correct post
                            Post p = fact.createPostfromDBSnapshot(d);
                            if (p != null) {
                                searchedPosts.add(p);
                            }
                        }
                        // add search posts to the feed
                        RecycleFeedAdapter recycleFeedAdapter = new RecycleFeedAdapter(getContext(), searchedPosts);
                        feed.setAdapter(recycleFeedAdapter);
                        feed.setLayoutManager(new LinearLayoutManager(getContext()));
                    } else {
                        // If you get to this point, an attribute hasn't been indexed
                        Toast.makeText(getContext(), "QUERY FAILED", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        // Set Feed Object
        feed = requireView().findViewById(R.id.feed_recycler);

        // Loads Posts from Database and Displays them on Feed Object
        getAllPosts();
    }

    /**
     * Loads posts for Database, onComplete, will set the Feed Object Adapter to Display them
     */
    public void getAllPosts() {
        CollectionReference docs = db.collection("posts");
        final AVLPosts[] tree = {new AVLPosts()};
        // Query from DB
        docs.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Casting Map Representation to Post Objects
                PostFactory fact = new PostFactory();
                for (QueryDocumentSnapshot d : task.getResult()) {
                    // Uses PostFactory to create correct post
                    Post p = fact.createPostfromDBQuerySnapshot(d);
                    tree[0] = tree[0].insert(p);
                }
                // Setting Recycle Feeds adapter to display
                RecycleFeedAdapter recycleFeedAdapter = new RecycleFeedAdapter(getContext(), tree[0].iterator());
                feed.setAdapter(recycleFeedAdapter);
            } else {
                // If Query was unsuccessful, display and empty feed
                RecycleFeedAdapter recycleFeedAdapter = new RecycleFeedAdapter(getContext(), new ArrayList<>());
                feed.setAdapter(recycleFeedAdapter);
            }
            feed.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }
}