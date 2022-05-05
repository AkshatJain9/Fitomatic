package com.ajsmdllz.fitomatic.Posts.typesOfPosts;

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
import com.ajsmdllz.fitomatic.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class IndividualPostFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private FirebaseAuth mAuth;
    FirebaseFirestore db;

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
        EditText bio = getView().findViewById(R.id.createDescriptionSingle);

        // Create Post Button
        Button createPost = getView().findViewById(R.id.createPostSingle);
        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This is where checks will be needed and then creating the post will happen
                // FIXME -- Need to implement firebase Post stuff here (not sure how to do image handling)
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
//        String selectedActivity = (String) parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}