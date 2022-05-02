package com.ajsmdllz.fitomatic.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ajsmdllz.fitomatic.MainActivity;
import com.ajsmdllz.fitomatic.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragment extends Fragment {
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
        return inflater.inflate(R.layout.fragment_profile, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView nameText = getView().findViewById(R.id.nameText);

        // Getting user's info from Firebase
        db.collection("users").document(mAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                String firstName = task.getResult().getString("firstname");
                String lastName = task.getResult().getString("lastname");
                String bio = task.getResult().getString("bio");
                String gender = task.getResult().getString("gender");
                int age = task.getResult().getDouble("age").intValue();

                nameText.setText(firstName+" "+lastName);

                //Display User information
//              TextView tvProfileTitle = findViewById(R.id.profileName);
//              /tvProfileTitle.setText("Displaying basic user info:\n " +
//                        "Email: "+mAuth.getCurrentUser().getEmail()+"\n " +
//                        "Full Name: "+firstName+" "+lastName+"\n" +
//                        "bio: "+bio+"\n" +
//                        "gender: "+gender+"\n" +
//                        "age: "+age);

            } else {
                Toast.makeText(getContext(), "(Something went wrong!) Should not get here - DENI", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void logout(View v) {
        mAuth.signOut();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }
}