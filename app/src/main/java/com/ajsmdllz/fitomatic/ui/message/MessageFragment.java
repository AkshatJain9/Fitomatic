package com.ajsmdllz.fitomatic.ui.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ajsmdllz.fitomatic.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Map;

public class MessageFragment extends Fragment {
    FirebaseFirestore db;
    ArrayList<String> emails;
    ArrayAdapter emailAdapter;
    ListView ls;
    FirebaseAuth mAuth;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container,false);
        db = FirebaseFirestore.getInstance();
        ls = view.findViewById(R.id.ListView);
        mAuth = FirebaseAuth.getInstance();
        emails = new ArrayList<>();

        CollectionReference userCollection = db.collection("users");
        // Queries all Emails for FireStore to Display
        userCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot d : task.getResult()) {
                    Map<String, Object> map = d.getData();
                    emails.add((String) map.get("email"));
                }
                // Remove current user's email
                emails.remove(mAuth.getCurrentUser().getEmail());

                // Query all Blocked Users and Remove them too
                DocumentReference userDocument = userCollection.document(mAuth.getCurrentUser().getEmail());
                userDocument.get().addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        DocumentSnapshot d = task1.getResult();
                        Map<String, Object> userAttributes = d.getData();
                        ArrayList<String> userBlocked = (ArrayList<String>) userAttributes.get("blocked");
                        emails.removeAll(userBlocked);
                    }
                    // GET USER OBJECT



                    emailAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, emails);
                    ls.setAdapter(emailAdapter);
                });
            }
        });

        // When an email is clicked start a direct message activity with that email as the recipient
        ls.setOnItemClickListener((adapterView, view1, i, l) -> {
            String recip = emails.get(i);
            Intent intent = new Intent(getContext(), DirectMessage.class);
            intent.putExtra("recip", recip);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}