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

import com.ajsmdllz.fitomatic.Posts.EventActivity;
import com.ajsmdllz.fitomatic.Posts.Post;
import com.ajsmdllz.fitomatic.Posts.PostHostActivity;
import com.ajsmdllz.fitomatic.Posts.SingleActivity;
import com.ajsmdllz.fitomatic.Posts.SmallGroupActivity;
import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.RecycleFeedAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;


public class PostFragment extends Fragment {
    ArrayList<Post> userPosts = new ArrayList<>();
    FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private String email;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        email = mAuth.getCurrentUser().getEmail();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_post, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PostHostActivity.class);
                startActivity(intent);
            }
        };

        View add = getView().findViewById(R.id.addPostIcon);
        add.setOnClickListener(listener);


    }

    public void populateUsersPosts() {
        ArrayList<String> postIDs = new ArrayList<>();
        db.collection("users").document(email).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                postIDs.addAll((ArrayList<String>) task.getResult().get("posts"));

                userPosts = new ArrayList<>();

                for (String id : postIDs) {
                    db.collection("posts").document(id).get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            DocumentSnapshot temp = task1.getResult();
                            Map<String, Object> map = temp.getData();
                            Post p;
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
                            userPosts.add(p);
                        }
//                        RecyclerView followingFeed = getView().findViewById(R.id.followingFeed);
//                        RecycleFeedAdapter recycleFeedAdapter = new RecycleFeedAdapter(getContext(), userPosts);
//                        followingFeed.setAdapter(recycleFeedAdapter);
//                        followingFeed.setLayoutManager(new LinearLayoutManager(getContext()));
                    });

                }

            }
        });
    }


}