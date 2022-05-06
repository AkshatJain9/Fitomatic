package com.ajsmdllz.fitomatic.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.ajsmdllz.fitomatic.AVLPosts;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajsmdllz.fitomatic.FeedAdapter;
import com.ajsmdllz.fitomatic.Posts.Post;
import com.ajsmdllz.fitomatic.Posts.SingleActivity;
import com.ajsmdllz.fitomatic.Posts.SmallGroupActivity;
import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.Registration.User;
import com.ajsmdllz.fitomatic.Search.SearchTokenizer;
import com.ajsmdllz.fitomatic.Search.SimpleTokenizer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

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

        // FIX MEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        // Pass into tokenizer
        tvSearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchTokenizer tokens = new SearchTokenizer(query);
                Toast.makeText(getContext().getApplicationContext(), (CharSequence) tokens.tokenize(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        // Home Button (sends user to home/main page)

        // Feed
        RecyclerView feed = getView().findViewById(R.id.feed_recycler);

        //ArrayList<Post> users = getPosts(); // line causes crashes
        ArrayList<Post> users = new ArrayList<>();

        //initialise with a user
        users.add(new SingleActivity("email","(email, 0)", "Title", "Description", "date", "activity", 0));
        users.add(new SmallGroupActivity("testing@testing1.com","(testing@testing1.com, 1)", "Title", "Description", "", "", "", "", new ArrayList<>(), 1, 1));
//        users.add(new EventActivity((new User("b", "b", "b", "b", 2, "m", new ArrayList<String>(), new ArrayList<Post>())), "Title", "Description", "date", new ArrayList<String>(), "", "", new ArrayList<String>(), 0, 0 ,0));

        RecycleFeedAdapter recycleFeedAdapter = new RecycleFeedAdapter(getContext(), users);

        feed.setAdapter(recycleFeedAdapter);
        feed.setLayoutManager(new LinearLayoutManager(getContext()));
        // End of Feed Code
    }

    /**
     * Returns all Posts in Array Form
     * @return AVL Tree of Posts
     */
    public AVLPosts getallPosts() {
        CollectionReference docs = db.collection("posts");
        final AVLPosts[] tree = {new AVLPosts()};
        docs.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot d : task.getResult()) {
                        tree[0] = tree[0].insert(d.toObject(Post.class));
                    }
                }
            }
        });
        return tree[0];
    }

    public ArrayList<Post> getPosts() {
        AVLPosts tree = getallPosts();
        return tree.iterator();
    }

}