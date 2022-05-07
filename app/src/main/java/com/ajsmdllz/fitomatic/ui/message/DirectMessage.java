package com.ajsmdllz.fitomatic.ui.message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.ajsmdllz.fitomatic.P2PMessaging.Message;
import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.Registration.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DirectMessage extends AppCompatActivity {
    FirebaseFirestore db;
    ArrayList<String> messages;
    ArrayAdapter<String> messageAdapter;
    String recip;
    String sender;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_message);
        db = FirebaseFirestore.getInstance();
        // Getting the recipient value
        Intent intent = getIntent();
        recip = intent.getStringExtra("recip");

        mAuth = FirebaseAuth.getInstance();
        sender = mAuth.getCurrentUser().getEmail();

        messages = new ArrayList<>();

        // Creating Session on Sender Side / Querying all messages if exists
        DocumentReference ref = db.collection("users").document(sender);
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot d = task.getResult();
                    Map<String, Object> mLog = d.getData();
                    HashMap<String,  ArrayList<HashMap<String, String>>> messageLog = (HashMap<String,  ArrayList<HashMap<String, String>>>) mLog.get("messages");

                    if (messageLog != null && messageLog.containsKey(recip) && messageLog.get(recip) != null) {
                        ArrayList<HashMap<String, String>> pasts = messageLog.get(recip);
                        for (HashMap<String, String> m : pasts) {
                            messages.add(m.get("message"));
                        }
                        // If user has never messaged specific individual before
                    } else {
                        if (messageLog == null) {
                            HashMap<String, ArrayList<Message>> newMap = new HashMap<>();
                            newMap.put(recip, new ArrayList<>());
                            ref.update("messages", newMap);
                        } else {
                            messageLog.put(recip, new ArrayList<>());
                            ref.update("messages", messageLog);
                        }
                    }
                }
            }
        });

        DocumentReference ref1 = db.collection("users").document(recip);
        ref1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot d = task.getResult();
                    Map<String, Object> mLog = d.getData();
                    HashMap<String,  ArrayList<HashMap<String, String>>> messageLog = (HashMap<String,  ArrayList<HashMap<String, String>>>) mLog.get("messages");
                    if (messageLog == null) {
                        HashMap<String, ArrayList<Message>> newMap = new HashMap<>();
                        newMap.put(sender, new ArrayList<>());
                        ref1.update("messages", newMap);
                        return;
                    }
                    if (!messageLog.containsKey(sender) || messageLog.get(sender) == null) {
                        messageLog.put(sender, new ArrayList<>());
                        ref1.update("messages", messageLog);
                    }
                }
            }
        });


        ListView messageView = (ListView) findViewById(R.id.MessageBoard);
        messageAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, messages);
        messageView.setAdapter(messageAdapter);
        messageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }

    public void SendMessage (View v) {
        EditText messagetoSend = findViewById(R.id.Message);
        String messagetoSendString = messagetoSend.getText().toString();

        DocumentReference currUser = db.collection("users").document(sender);

        currUser.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot d = task.getResult();
                    Map<String, Object> mLog = d.getData();
                    HashMap<String, ArrayList<Message>> messageLog = (HashMap<String, ArrayList<Message>>) mLog.get("messages");
                    messageLog.get(recip).add(new Message(sender, messagetoSendString));
                    currUser.update("messages", messageLog);
                }
            }
        });

        DocumentReference sendUser = db.collection("users").document(recip);
        sendUser.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot d = task.getResult();
                    Map<String, Object> mLog = d.getData();
                    HashMap<String, ArrayList<Message>> messageLog = (HashMap<String, ArrayList<Message>>) mLog.get("messages");
                    messageLog.get(sender).add(new Message(sender, messagetoSendString));
                    sendUser.update("messages", messageLog);
                }
            }
        });

        messages.add(messagetoSendString);
        messageAdapter.notifyDataSetChanged();
        messagetoSend.setText("");
    }


}