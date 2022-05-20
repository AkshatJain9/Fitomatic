package com.ajsmdllz.fitomatic.ui.post;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajsmdllz.fitomatic.Posts.Post;
import com.ajsmdllz.fitomatic.Posts.PostFactory;
import com.ajsmdllz.fitomatic.Posts.PostHostActivity;
import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.RecycleFeedAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;


public class PostFragment extends Fragment {
    ArrayList<Post> userPosts = new ArrayList<>();
    FirebaseFirestore db;
    private String email;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_post, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View.OnClickListener listener = v -> {
            Intent intent = new Intent(getActivity(), PostHostActivity.class);
            startActivity(intent);
        };

        View add = requireView().findViewById(R.id.addPostIcon);
        add.setOnClickListener(listener);

        populateUsersPosts();

    }

    public void populateUsersPosts() {
        ArrayList<String> postIDs = new ArrayList<>();
        db.collection("users").document(email).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().get("posts") != null) {
                    postIDs.addAll((ArrayList<String>) task.getResult().get("posts"));
                }

                userPosts = new ArrayList<>();

                for (String id : postIDs) {
                    db.collection("posts").document(id).get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            DocumentSnapshot temp = task1.getResult();
                            PostFactory fact = new PostFactory();
                            // Uses PostFactory to create correct post
                            Post p = fact.createPostfromDBSnapshot(temp);
                            if (p != null) {
                                userPosts.add(p);
                            }
                        }
                        RecyclerView postRecycler = requireView().findViewById(R.id.myPostRecycler);
                        RecycleFeedAdapter recycleFeedAdapter = new RecycleFeedAdapter(getContext(), userPosts);
                        postRecycler.setAdapter(recycleFeedAdapter);
                        postRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    });

                }

            }
        });
    }


}