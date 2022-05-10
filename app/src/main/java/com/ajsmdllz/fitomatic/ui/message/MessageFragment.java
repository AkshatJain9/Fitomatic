package com.ajsmdllz.fitomatic.ui.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.Registration.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //create the recycler view
        RecyclerView messageRecycler = getView().findViewById(R.id.messageRecyclerView);

        //update users to contain the list of users
        users = new ArrayList<>();
        users.add(new User("Brian", "Smith", "", "", 2,"",new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>()));
        users.add(new User("Brian", "Smith", "", "", 2,"",new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>()));
        users.add(new User("Brian", "Smith", "", "", 2,"",new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>()));
        users.add(new User("Brian", "Smith", "", "", 2,"",new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>()));
        users.add(new User("Brian", "Smith", "", "", 2,"",new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>()));
        users.add(new User("Brian", "Smith", "", "", 2,"",new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>()));
        users.add(new User("Brian", "Smith", "", "", 2,"",new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>()));
        users.add(new User("Brian", "Smith", "", "", 2,"",new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>()));
        users.add(new User("Brian", "Smith", "", "", 2,"",new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>()));
        users.add(new User("Brian", "Smith", "", "", 2,"",new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>()));
        users.add(new User("Brian", "Smith", "", "", 2,"",new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new ArrayList<>()));

        //add them to the adapter
        MessageRecyclerAdapter adapter = new MessageRecyclerAdapter(getContext(), users);
        messageRecycler.setAdapter(adapter);
        messageRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    //TODO
    /**
     * getUsers accesses the database and updates the users list to contain a list of all users or
     * potentially all users the current user follows
     */
    private void getUsers(){
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        users = new ArrayList<>();

//        CollectionReference userCollection = db.collection("users");
//        // Queries all Emails for FireStore to Display
//        userCollection.get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                for (QueryDocumentSnapshot d : task.getResult()) {
//                    Map<String, Object> map = d.getData();
//                    emails.add((String) map.get("firstname") + (String) map.get("lastname"));
//                }
//                // Remove current user's email
//                emails.remove(mAuth.getCurrentUser().getEmail());
//
//                // Query all Blocked Users and Remove them too
//                DocumentReference userDocument = userCollection.document(mAuth.getCurrentUser().getEmail());
//                userDocument.get().addOnCompleteListener(task1 -> {
//                    if (task1.isSuccessful()) {
//                        DocumentSnapshot d = task1.getResult();
//                        Map<String, Object> userAttributes = d.getData();
//                        ArrayList<String> userBlocked = (ArrayList<String>) userAttributes.get("blocked");
//                        emails.removeAll(userBlocked);
//                    }
//                    // GET USER OBJECT
//
//
//
//                    emailAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, emails);
//                    ls.setAdapter(emailAdapter);
//                });
//            }
//        });
//
//        // When an email is clicked start a direct message activity with that email as the recipient
//        ls.setOnItemClickListener((adapterView, view1, i, l) -> {
//            String recip = emails.get(i);
//            Intent intent = new Intent(getContext(), DirectMessage.class);
//            intent.putExtra("recip", recip);
//            startActivity(intent);
//        });
    }
}