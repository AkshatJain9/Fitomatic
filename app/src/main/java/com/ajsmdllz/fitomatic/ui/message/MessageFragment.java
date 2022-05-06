package com.ajsmdllz.fitomatic.ui.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.Registration.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageFragment extends Fragment {
    FirebaseFirestore db;
    ArrayList<String> emails;
    ArrayAdapter emailAdapter;
    ListView ls;
    FirebaseAuth mAuth;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container,false);
        db = FirebaseFirestore.getInstance();
        ls = view.findViewById(R.id.ListView);
        mAuth = FirebaseAuth.getInstance();
        emails = new ArrayList<>();
        CollectionReference docs = db.collection("users");
        docs.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot d : task.getResult()) {
                        Map<String, Object> map = d.getData();
                        emails.add((String) map.get("email"));
                    }
                    emails.remove(mAuth.getCurrentUser().getEmail());
//                    emails.removeAll() REMOVE ALL BLOCKED INDIVIDUALS HERE
                    emailAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, emails);
                    ls.setAdapter(emailAdapter);
                }
            }
        });

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String recip = emails.get(i);

                Intent intent = new Intent(getContext(), DirectMessage.class);
                intent.putExtra("recip", recip);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}