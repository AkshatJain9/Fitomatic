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

import java.util.Locale;

public class ProfileFragment extends Fragment {
    private FirebaseAuth mAuth;
    FirebaseFirestore db;
    private String email;

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
        email = mAuth.getCurrentUser().getEmail();
        Toast.makeText(getContext(), email, Toast.LENGTH_SHORT).show();

        // Getting user's info from Firebase
        db.collection("users").document(email).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                String firstName = task.getResult().getString("firstname");
                String lastName = task.getResult().getString("lastname");
//                String bio = task.getResult().getString("bio");

                nameText.setText(firstName+" "+lastName);
            } else {
                Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void logout(View v) {
        mAuth.signOut();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }
}