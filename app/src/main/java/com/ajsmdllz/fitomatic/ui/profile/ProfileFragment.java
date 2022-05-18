package com.ajsmdllz.fitomatic.ui.profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajsmdllz.fitomatic.MainActivity;
import com.ajsmdllz.fitomatic.Posts.EventActivity;
import com.ajsmdllz.fitomatic.Posts.Post;
import com.ajsmdllz.fitomatic.Posts.PostFactory;
import com.ajsmdllz.fitomatic.Posts.SingleActivity;
import com.ajsmdllz.fitomatic.Posts.SmallGroupActivity;
import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.RecycleFeedAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    private String email;
    private StorageReference mStorage;
    ArrayList<Post> followingPosts = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        mStorage = FirebaseStorage.getInstance().getReference();
        email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView nameText = requireView().findViewById(R.id.nameText);
        email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();

        // Getting user's info from Firebase
        db.collection("users").document(email).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                String firstName = task.getResult().getString("firstname");
                String lastName = task.getResult().getString("lastname");
                nameText.setText(firstName+" "+lastName);
            } else {
                Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });

        //Display user profile picture
        ImageView profilePic = requireView().findViewById(R.id.profilePicture);
        try {
            final File profileFile = File.createTempFile(email, "jpg");
            StorageReference picture = mStorage.child("pfpImages/" + email);
            picture.getFile(profileFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bits = BitmapFactory.decodeFile(profileFile.getAbsolutePath());
                    profilePic.setImageBitmap(bits);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Profile Picture Not Found", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageView logout = requireView().findViewById(R.id.logoutIcon);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //populate the following feed
        populateFollowingFeed();
    }

    private void populateFollowingFeed(){
        ArrayList<String> postIDs = new ArrayList<>();
        db.collection("users").document(email).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                postIDs.addAll((ArrayList<String>) task.getResult().get("following"));

                followingPosts = new ArrayList<>();

                for (String id : postIDs) {
                    db.collection("posts").document(id).get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            DocumentSnapshot temp = task1.getResult();
                            PostFactory fact = new PostFactory();
                            // Uses PostFactory to create correct post
                            Post p = fact.createPostfromDBSnapshot(temp);
                            if (p != null) {
                                followingPosts.add(p);
                            }
                        }
                        RecyclerView followingFeed = requireView().findViewById(R.id.followingFeed);
                        RecycleFeedAdapter recycleFeedAdapter = new RecycleFeedAdapter(getContext(), followingPosts);
                        followingFeed.setAdapter(recycleFeedAdapter);
                        followingFeed.setLayoutManager(new LinearLayoutManager(getContext()));
                    });

                }

            }
        });
    }
}