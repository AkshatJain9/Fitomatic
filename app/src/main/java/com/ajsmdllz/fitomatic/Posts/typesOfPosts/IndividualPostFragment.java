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
import android.widget.Spinner;
import android.widget.Toast;

import com.ajsmdllz.fitomatic.LoginSuccess;
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
import java.util.List;


public class IndividualPostFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    private String selectedActivity;
    private String email;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_individual_post, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Dropdown of activities
        Spinner activity = getView().findViewById(R.id.spinnerActivitySingle);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.activities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        activity.setAdapter(adapter);
        // Sets listener for the spinner
        activity.setOnItemSelectedListener(this);

        EditText title = getView().findViewById(R.id.createTitleSingle);
        EditText description = getView().findViewById(R.id.createDescriptionSingle);
        EditText date = getView().findViewById(R.id.createDateSingle);
        email = mAuth.getCurrentUser().getEmail();


        // Create Post Button
        Button createPost = getView().findViewById(R.id.createPostSingle);
        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This is where checks will be needed and then creating the post will happen
                if (title.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), "Please enter a title!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (description.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), "Please enter a description!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (date.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), "Please enter a date!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // Successfully create a post
                    Toast.makeText(getView().getContext(), "Created Post!", Toast.LENGTH_SHORT).show();

                    PostFactory newPost = new PostFactory();
                    ArrayList<String> activites = new ArrayList<>();
                    activites.add(selectedActivity);
                    ArrayList<String> followers = new ArrayList<>();
                    // Add post to database
                    db.collection("users").document(email).get().addOnCompleteListener(task -> {
                        if (task.isSuccessful() && task.getResult() != null) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                ArrayList<String> posts = (ArrayList<String>) document.get("posts");
                                Toast.makeText(getContext(), posts.size()+"", Toast.LENGTH_SHORT).show();
                                Post post = newPost.createPost(mAuth.getCurrentUser().getEmail(),"("+email+", "+posts.size()+")",title.getText().toString(),description.getText().toString(),date.getText().toString(),activites, "", "", followers,0, 1,0);
                                db.collection("posts").document("("+email+", "+posts.size()+")").set(post);
                                posts.add("("+email+", "+posts.size()+")");
                                Toast.makeText(getContext(), posts.toString()+"", Toast.LENGTH_SHORT).show();
                                db.collection("users").document(email).update("posts", posts);
                            } else {
                                ArrayList<String> posts = new ArrayList<String>();
                                Post post = newPost.createPost(mAuth.getCurrentUser().getEmail(),"("+email+", "+posts.size()+")",title.getText().toString(),description.getText().toString(),date.getText().toString(),activites, "", "", followers,0, 1,0);
                                posts.add("("+email+", "+posts.size()+")");
                                Toast.makeText(getContext(), posts.get(0).toString(), Toast.LENGTH_SHORT).show();
                                db.collection("users").document(email).update("posts", posts);
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
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        selectedActivity = (String) adapterView.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}