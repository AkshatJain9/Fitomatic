package com.ajsmdllz.fitomatic.Posts.typesOfPosts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.ajsmdllz.fitomatic.Posts.Post;
import com.ajsmdllz.fitomatic.Posts.PostFactory;
import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.hostActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.Objects;

public class LargePostFragment extends Fragment {
    /**
     * This class is used to create Large (Event) posts.
     * Content linked to fragment_large_post.xml (page where you create the post)
     */
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    private String email;
    private int eventPrice;
    private int eventMaxPart;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_large_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initialize variables for multiple activity selection
        email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
        SeekBar priceBar = requireView().findViewById(R.id.priceBar);
        TextView price = requireView().findViewById(R.id.price);
        EditText title = requireView().findViewById(R.id.createTitleEvent);
        EditText description = requireView().findViewById(R.id.createDescriptionEvent);
        EditText date = requireView().findViewById(R.id.date);
        EditText location = requireView().findViewById(R.id.locationEvent);


        // initialise variables for multi select activities
        ArrayList<Integer> activityList = new ArrayList<>();
        String[] activities = {"Running", "Walking", "Weight Lifting", "Rowing", "Yoga", "Soccer", "Hiking",
                "Gymnastics", "AFL", "Tennis", "Rugby", "Surfing", "Golf", "Bowling", "Karate",
                "Bouldering", "Rock Climbing", "Cycling", "Mountain Biking", "Swimming",
                "Cricket", "Judo", "Tai Quan Dao"};
        TextView popUptextView = requireView().findViewById(R.id.multiActivityDropdown);
        boolean[] selectedActivities = new boolean[activities.length];
        popUptextView.setKeyListener(null);
        popUptextView.setOnClickListener(view1 -> {
            // Initialize alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Select Activities");
            // set dialog non cancelable
            builder.setCancelable(false);

            builder.setMultiChoiceItems(activities, selectedActivities, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                    // check condition
                    if (b) {
                        // when checkbox selected add in activity list
                        activityList.add(i);
                    } else {
                        // when checkbox unselected remove position from langList
                        activityList.remove(Integer.valueOf(i));
                    }
                }
            });

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Initialize string builder
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int j = 0; j < activityList.size(); j++) {
                        // concat array value
                        stringBuilder.append(activities[activityList.get(j)]);
                        // check condition
                        if (j != activityList.size() - 1) {
                            // add comma between elements
                            stringBuilder.append(", ");
                        }
                    }
                    // set text on textView
                    popUptextView.setText(stringBuilder.toString());
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // dismiss dialog
                    dialogInterface.dismiss();
                }
            });
            builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // use for loop
                    for (int j = 0; j < selectedActivities.length; j++) {
                        // remove all selection
                        selectedActivities[j] = false;
                        // clear activity list
                        activityList.clear();
                        // clear text view value
                        popUptextView.setText("");
                    }
                }
            });
            // show dialog
            builder.show();
        });



        // Create Post Button handler
        Button createPost = requireView().findViewById(R.id.createPostEvent);
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
                } else if (eventMaxPart == 0) {
                    Toast.makeText(getContext(), "Please select max participants!", Toast.LENGTH_SHORT).show();
                }else {
                    // Successfully create a post
                    Toast.makeText(requireView().getContext(), "Created Post!", Toast.LENGTH_SHORT).show();

                    // Using post factory to create different posts
                    PostFactory newPost = new PostFactory();

                    ArrayList<String> activites = new ArrayList<>();
                    for (Integer elem: activityList) {
                        activites.add(activities[elem]);
                    }

                    ArrayList<String> followers = new ArrayList<>();
                    ArrayList<String> liked = new ArrayList<>();
                    // Add post to database
                    db.collection("users").document(email).get().addOnCompleteListener(task -> {
                        if (task.isSuccessful() && task.getResult() != null) {
                            ArrayList<String> posts = (ArrayList<String>) task.getResult().get("posts");
                            if (posts != null) {
                                Post post = newPost.createPost(mAuth.getCurrentUser().getEmail(),"("+email+", "+posts.size()+")",title.getText().toString(),description.getText().toString(),date.getText().toString(),activites, location.getText().toString(), followers,eventPrice, eventMaxPart,0, liked);
                                db.collection("posts").document("("+email+", "+posts.size()+")").set(post);
                                posts.add("("+email+", "+posts.size()+")");
                                db.collection("users").document(email).update("posts", posts);
                            } else {
                                Post post = newPost.createPost(mAuth.getCurrentUser().getEmail(),"("+email+", "+0+")",title.getText().toString(),description.getText().toString(),date.getText().toString(),activites, location.getText().toString(), followers,eventPrice,eventMaxPart,0, liked);
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


        priceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                System.out.println(i);
                price.setText("Price $" + i);
                eventPrice = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        SeekBar maxPartBar = requireView().findViewById(R.id.maxPartBar);
        TextView maxPart = requireView().findViewById(R.id.maxPart);
        maxPartBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                System.out.println(i);
                maxPart.setText("Max Participants " + i);
                eventMaxPart = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}