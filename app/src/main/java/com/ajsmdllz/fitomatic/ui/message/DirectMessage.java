package com.ajsmdllz.fitomatic.ui.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.ajsmdllz.fitomatic.P2PMessaging.Message;
import com.ajsmdllz.fitomatic.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DirectMessage extends AppCompatActivity {
    FirebaseFirestore db;
    ArrayList<Message> messages;
    ArrayAdapter<String> messageAdapter;
    String recip;
    String sender;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_message);
        db = FirebaseFirestore.getInstance();

        // Getting the recipient's email for indexing
        Intent intent = getIntent();
        recip = intent.getStringExtra("recip");
        // Getting the sender's email for indexing
        mAuth = FirebaseAuth.getInstance();
        sender = mAuth.getCurrentUser().getEmail();

        messages = new ArrayList<>();

        // Creating Session on Sender Side / Querying all messages if exists
        DocumentReference senderRef = db.collection("users").document(sender);
        senderRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Store User Object when Successful
                DocumentSnapshot d = task.getResult();
                Map<String, Object> mLog = d.getData();
                HashMap<String,  ArrayList<HashMap<String, String>>> messageLog = (HashMap<String,  ArrayList<HashMap<String, String>>>) mLog.get("messages");
                // If User has Messaged Recipient Before, then get all the messages
                if (messageLog != null && messageLog.containsKey(recip) && messageLog.get(recip) != null) {
                    ArrayList<HashMap<String, String>> pasts = messageLog.get(recip);
                    for (HashMap<String, String> m : pasts) {
                        messages.add(new Message(m.get("sender"), m.get("message")));
                    }

                    populateMessages();
                // If user has never messaged specific individual before, start storing messages (new session)
                } else {
                    // If Sender hasn't messaged ANYONE before, then a new hasmap object needs to be made
                    if (messageLog == null) {
                        HashMap<String, ArrayList<Message>> newMap = new HashMap<>();
                        newMap.put(recip, new ArrayList<>());
                        senderRef.update("messages", newMap);
                    } else {
                        messageLog.put(recip, new ArrayList<>());
                        senderRef.update("messages", messageLog);
                    }
                }
            }
        });

        DocumentReference recipRef = db.collection("users").document(recip);
        recipRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Now storing the Recipient User Object
                DocumentSnapshot d = task.getResult();
                Map<String, Object> mLog = d.getData();
                HashMap<String,  ArrayList<HashMap<String, String>>> messageLog = (HashMap<String,  ArrayList<HashMap<String, String>>>) mLog.get("messages");
                // If the recipient hasn't messaged ANYONE before then a new HashMap needs to be made
                if (messageLog == null) {
                    HashMap<String, ArrayList<Message>> newMap = new HashMap<>();
                    newMap.put(sender, new ArrayList<>());
                    recipRef.update("messages", newMap);
                    return;
                }
                // If the recipient has messaged someone before, just need to add a new KV pair for this specific User
                if (!messageLog.containsKey(sender) || messageLog.get(sender) == null) {
                    messageLog.put(sender, new ArrayList<>());
                    recipRef.update("messages", messageLog);
                }
            }
        });
    }


    /**
     * Given the message provided in the input field, appropriately stores it in the database and displays on Sender Side immediately
     */
    public void SendMessage (View v) {
        EditText messagetoSend = findViewById(R.id.Message);
        String messagetoSendString = messagetoSend.getText().toString();


        DocumentReference currUser = db.collection("users").document(sender);
        currUser.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Because a DB side session is made when Activity is made, we can just update field in Database
                DocumentSnapshot d = task.getResult();
                Map<String, Object> mLog = d.getData();
                HashMap<String, ArrayList<Message>> messageLog = (HashMap<String, ArrayList<Message>>) mLog.get("messages");
                messageLog.get(recip).add(new Message(sender, messagetoSendString));
                currUser.update("messages", messageLog);
            }
        });

        DocumentReference sendUser = db.collection("users").document(recip);
        sendUser.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Because a DB side session is made when Activity is made, we can just update field in Database
                DocumentSnapshot d = task.getResult();
                Map<String, Object> mLog = d.getData();
                HashMap<String, ArrayList<Message>> messageLog = (HashMap<String, ArrayList<Message>>) mLog.get("messages");
                messageLog.get(sender).add(new Message(sender, messagetoSendString));
                sendUser.update("messages", messageLog);
            }
        });

        messages.add(new Message(sender, messagetoSendString));
        populateMessages();
        messagetoSend.setText("");
    }

    private void populateMessages(){
        //Populate the recycler with the messages
        RecyclerView messageRecycler = findViewById(R.id.directMessagesRecycler);
        DirectMessageRecyclerAdapter adapter = new DirectMessageRecyclerAdapter(this, messages);
        messageRecycler.setAdapter(adapter);
        messageRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

}