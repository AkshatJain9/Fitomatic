package com.ajsmdllz.fitomatic.Posts.typesOfPosts;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ajsmdllz.fitomatic.Posts.Post;
import com.ajsmdllz.fitomatic.Posts.PostFactory;
import com.ajsmdllz.fitomatic.Posts.SingleActivity;
import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.hostActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;


public class SmallPostFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    private String selectedActivity;
    private String email;
    private int maxPart;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_small_post, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Dropdown of activities
        Spinner activity = requireView().findViewById(R.id.spinnerActivitySmall);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.activities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        activity.setAdapter(adapter);
        // Sets listener for the spinner
        activity.setOnItemSelectedListener(this);

        EditText title = requireView().findViewById(R.id.createTitleSmall);
        EditText description = requireView().findViewById(R.id.createDescriptionSmall);
        EditText date = requireView().findViewById(R.id.createDateSmall);
        EditText location = requireView().findViewById(R.id.createLocationSmall);
        email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();

        // Create Post Button
        Button createPost = requireView().findViewById(R.id.createPostSmall);
        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This is where checks will be needed and then creating the post will happen
                if (title.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), "Please enter a title!", Toast.LENGTH_SHORT).show();
                } else if (description.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), "Please enter a description!", Toast.LENGTH_SHORT).show();
                }else if (date.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), "Please enter a date!", Toast.LENGTH_SHORT).show();
                } else if (location.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), "Please enter a location!", Toast.LENGTH_SHORT).show();
                } else if (maxPart == 0) {
                    Toast.makeText(getContext(), "Please select max participants!", Toast.LENGTH_SHORT).show();
                }else {
                    // Successfully create a post
                    Toast.makeText(requireView().getContext(), "Created Post!", Toast.LENGTH_SHORT).show();

                    // Using post factory to create different posts
                    PostFactory newPost = new PostFactory();

                    // Adding the selected activity to the
                    ArrayList<String> activites = new ArrayList<>();
                    activites.add(selectedActivity);

                    ArrayList<String> liked = new ArrayList<>();
                    ArrayList<String> followers = new ArrayList<>();
                    // Add post to database
                    db.collection("users").document(email).get().addOnCompleteListener(task -> {
                        if (task.isSuccessful() && task.getResult() != null) {
                            ArrayList<String> posts = (ArrayList<String>) task.getResult().get("posts");
                            if (posts != null) {
                                Post post = newPost.createPost(mAuth.getCurrentUser().getEmail(),"("+email+", "+posts.size()+")",title.getText().toString(),description.getText().toString(),date.getText().toString(),activites, location.getText().toString(), followers,-1, maxPart,0, liked);
                                // Adding the post to Firebase
                                db.collection("posts").document("("+email+", "+posts.size()+")").set(post);
                                posts.add("("+email+", "+posts.size()+")");
                                // Adding the post to list of posts that the user has made
                                db.collection("users").document(email).update("posts", posts);
                            } else {
                                // This is the user's first post so it needs to be setup slightly differently to any other, i.e. posts size is 0
                                Post post = newPost.createPost(mAuth.getCurrentUser().getEmail(),"("+email+", "+0+")",title.getText().toString(),description.getText().toString(),date.getText().toString(),activites, location.getText().toString(), followers,-1,maxPart,0, liked);
                                ArrayList<String> firstPost = new ArrayList<>();
                                firstPost.add("("+email+", "+0+")");
                                db.collection("posts").document("("+email+", "+0+")").set(post);
                                db.collection("users").document(email).update("posts", firstPost);
                            }
                        } else {
                            Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    // Go back to main page
                    startActivity(new Intent(getContext(), hostActivity.class));
                }
            }
        });

        SeekBar maxPartBar = requireView().findViewById(R.id.maxPartBarSmall);
        TextView maxPartSmall = requireView().findViewById(R.id.maxPartSmall);
        maxPartBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                System.out.println(i);
                maxPartSmall.setText("Max Participants " + i);
                maxPart = i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        selectedActivity = (String) adapterView.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}