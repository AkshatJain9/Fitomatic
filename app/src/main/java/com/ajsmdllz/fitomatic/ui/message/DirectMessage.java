package com.ajsmdllz.fitomatic.ui.message;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

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
        sender = mAuth.getCurrentUser().getEmail(); //COULD BREAK

        messages = new ArrayList<>();
        DocumentReference ref = db.collection("users").document(sender);
        HashMap<String, ArrayList<Message>> messageLog = (HashMap<String, ArrayList<Message>>) ref.get().getResult().getData().get("messages");

        DocumentReference refR = db.collection("users").document(recip);
        HashMap<String, ArrayList<Message>> messageLogR = (HashMap<String, ArrayList<Message>>) refR.get().getResult().getData().get("messages");

        // If user has been messaged before
        if (messageLog != null && messageLog.containsKey(recip) && messageLog.get(recip) != null) {
            ArrayList<Message> pasts = messageLog.get(recip);
            for (Message m : pasts) {
                messages.add(m.message);
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

        if (messageLogR == null) {
            HashMap<String, ArrayList<Message>> newMap = new HashMap<>();
            newMap.put(sender, new ArrayList<>());
            refR.update("messages", newMap);
        } else {
            messageLogR.put(sender, new ArrayList<>());
            refR.update("messages", messageLog);
        }

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
        DocumentReference sendUser = db.collection("users").document(recip);

        HashMap<String, ArrayList<Message>> messageLog = (HashMap<String, ArrayList<Message>>) currUser.get().getResult().getData().get("messages");
        messageLog.get(recip).add(new Message(sender, messagetoSendString));


        HashMap<String, ArrayList<Message>> messageLogR = (HashMap<String, ArrayList<Message>>) sendUser.get().getResult().getData().get("messages");
        messageLogR.get(recip).add(new Message(sender, messagetoSendString));

        messages.add(messagetoSendString);

        messageAdapter.notifyDataSetChanged();
        messagetoSend.setText("");

    }


}