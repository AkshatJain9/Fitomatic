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
import com.ajsmdllz.fitomatic.Posts.EventActivity;
import com.ajsmdllz.fitomatic.Posts.Post;
import com.ajsmdllz.fitomatic.Posts.SingleActivity;
import com.ajsmdllz.fitomatic.Posts.SmallGroupActivity;
import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.RecycleFeedAdapter;
import com.ajsmdllz.fitomatic.Search.DBQuery;
import com.ajsmdllz.fitomatic.Search.Expressions.Exp;
import com.ajsmdllz.fitomatic.Search.SearchParser;
import com.ajsmdllz.fitomatic.Search.SearchTokenizer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class HomeFragment extends Fragment {
    FirebaseFirestore db;
    RecyclerView feed;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set up Search Bar
        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
        SearchView tvSearchBar = getView().findViewById(R.id.searchBar);
        tvSearchBar.setQueryHint("Search for something");

        tvSearchBar.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        // Pass into tokenizer
        tvSearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchTokenizer tokens = new SearchTokenizer(query);
                SearchParser parser = new SearchParser(tokens);
                Exp e = parser.parseStatement();
//                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show(); Keep for now
                ArrayList<Post> searchedPosts = new ArrayList<>();
                System.out.println(e);
                DBQuery queryHandler = DBQuery.getInstance();
                CollectionReference colref = db.collection("posts");
                Query r = queryHandler.getQuery(e, colref);
                r.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        ArrayList<DocumentSnapshot> docs = (ArrayList<DocumentSnapshot>) snapshot.getDocuments();
                        for (DocumentSnapshot d : docs) {
                            Post p;
                            Map<String, Object> map = d.getData();
                            if (map.keySet().size() == 8) {
                                p = new SingleActivity(
                                        (String) map.get("author"),
                                        (String) map.get("id"),
                                        (String) map.get("title"),
                                        (String) map.get("description"),
                                        (String) map.get("date"),
                                        (String) map.get("activity"),
                                        ((Long) map.get("likes")).intValue(),
                                        (ArrayList<String>) map.get("liked"));
                            } else if (map.keySet().size() == 11) {
                                p = new SmallGroupActivity(
                                        (String) map.get("author"),
                                        (String) map.get("id"),
                                        (String) map.get("title"),
                                        (String) map.get("description"),
                                        (String) map.get("date"),
                                        (String) map.get("activity"),
                                        (String) map.get("location"),
                                        (ArrayList<String>) map.get("followers"),
                                        ((Long) map.get("maxParticipants")).intValue(),
                                        ((Long) map.get("likes")).intValue(),
                                        (ArrayList<String>) map.get("liked"));
                            } else {
                                p = new EventActivity(
                                        (String) map.get("author"),
                                        (String) map.get("id"),
                                        (String) map.get("title"),
                                        (String) map.get("description"),
                                        (String) map.get("date"),
                                        (ArrayList<String>) map.get("activities"),
                                        (String) map.get("location"),
                                        (ArrayList<String>) map.get("followers"),
                                        ((Long) map.get("price")).intValue(),
                                        ((Long) map.get("maxParticipants")).intValue(),
                                        ((Long) map.get("likes")).intValue(),
                                        (ArrayList<String>) map.get("liked"));
                            }
                            searchedPosts.add(p);
                        }
                        // add search posts to the feed
                        RecycleFeedAdapter recycleFeedAdapter = new RecycleFeedAdapter(getContext(), searchedPosts);
                        feed.setAdapter(recycleFeedAdapter);
                        feed.setLayoutManager(new LinearLayoutManager(getContext()));

                    } else {
                        // If you get to this point, an attribute hasn't been indexed, please add on Firebase (or ask AJ)
                        Toast.makeText(getContext(), "QUERY FAILED", Toast.LENGTH_SHORT).show();
                    }
                    // TRY FROM HERE

                });



                return true;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        // Set Feed Object
        feed = getView().findViewById(R.id.feed_recycler);

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
                for (QueryDocumentSnapshot d : task.getResult()) {
                    Post p;
                    Map<String, Object> map = d.getData();

                    if (map.keySet().size() == 8) {
                        p = new SingleActivity(
                                (String) map.get("author"),
                                (String) map.get("id"),
                                (String) map.get("title"),
                                (String) map.get("description"),
                                (String) map.get("date"),
                                (String) map.get("activity"),
                                ((Long) map.get("likes")).intValue(),
                                (ArrayList<String>) map.get("liked"));
                    } else if (map.keySet().size() == 11) {
                        p = new SmallGroupActivity(
                                (String) map.get("author"),
                                (String) map.get("id"),
                                (String) map.get("title"),
                                (String) map.get("description"),
                                (String) map.get("date"),
                                (String) map.get("activity"),
                                (String) map.get("location"),
                                (ArrayList<String>) map.get("followers"),
                                ((Long) map.get("maxParticipants")).intValue(),
                                ((Long) map.get("likes")).intValue(),
                                (ArrayList<String>) map.get("liked"));
                    } else {
                        p = new EventActivity(
                                (String) map.get("author"),
                                (String) map.get("id"),
                                (String) map.get("title"),
                                (String) map.get("description"),
                                (String) map.get("date"),
                                (ArrayList<String>) map.get("activities"),
                                (String) map.get("location"),
                                (ArrayList<String>) map.get("followers"),
                                ((Long) map.get("price")).intValue(),
                                ((Long) map.get("maxParticipants")).intValue(),
                                ((Long) map.get("likes")).intValue(),
                                (ArrayList<String>) map.get("liked"));
                    }
                    tree[0] = tree[0].insert(p);
                }
                // Setting Recycle Feeds adapter to display
                RecycleFeedAdapter recycleFeedAdapter = new RecycleFeedAdapter(getContext(), tree[0].iterator());
                feed.setAdapter(recycleFeedAdapter);
                feed.setLayoutManager(new LinearLayoutManager(getContext()));
            } else {
                // If Query was unsuccessful, display and empty feed
                RecycleFeedAdapter recycleFeedAdapter = new RecycleFeedAdapter(getContext(), new ArrayList<>());
                feed.setAdapter(recycleFeedAdapter);
                feed.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
    }
}