package com.ajsmdllz.fitomatic.ui.message;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajsmdllz.fitomatic.P2PMessaging.Message;
import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.Registration.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MessageFragment extends Fragment {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private List<User> users;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container,false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUsers();
    }

    /**
     * getUsers accesses the database and updates the users list to contain a list of all users or
     * potentially all users the current user follows
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getUsers(){

        users = new ArrayList<>();
        ArrayList<String> emails = new ArrayList<>();

        CollectionReference userCollection = db.collection("users");
        // Queries all Emails for FireStore to Display
        userCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot d : task.getResult()) {
                    HashMap<String, Object> map = (HashMap<String, Object>) d.getData();
                    User u;
                    u = new User(
                            (String) map.get("firstname"),
                            (String) map.get("lastname"),
                            (String) map.get("email"),
                            (String) map.get("bio"),
                            ((Long) Objects.requireNonNull(map.get("age"))).intValue(),
                            (String) map.get("gender"),
                            (ArrayList<String>) map.get("interests"),
                            (ArrayList<String>) map.get("posts"),
                            (ArrayList<String>) map.get("blocked"),
                            (HashMap<String, ArrayList< Message >>) map.get("messages"),
                            (ArrayList<String>) map.get("following")
                    );
                    users.add(u);
                }

                String curr = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
                users.removeIf(u -> u.getEmail().equals(curr));

                // Remove current user's email
                emails.remove(curr);

                // Query all Blocked Users and Remove them too
                DocumentReference userDocument = userCollection.document(curr);
                userDocument.get().addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        DocumentSnapshot d = task1.getResult();
                        Map<String, Object> userAttributes = d.getData();
                        ArrayList<String> userBlocked = (ArrayList<String>) userAttributes.get("blocked");
                        users.removeIf(u -> userBlocked.contains(u.getEmail()));
                        emails.removeAll(userBlocked);

                        RecyclerView messageRecycler = requireView().findViewById(R.id.messageRecyclerView);
                        MessageRecyclerAdapter adapter = new MessageRecyclerAdapter(getContext(), users);
                        messageRecycler.setAdapter(adapter);
                        messageRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                });
            }
        });
    }
}