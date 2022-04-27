package com.ajsmdllz.fitomatic;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class FeedAdapter extends ArrayAdapter<FirebaseUser> {
    private ArrayList<FirebaseUser> users;
    private int layoutResourceId;
    private Context context;
    FirebaseFirestore db;

    public FeedAdapter(Context context, int layoutResourceId, ArrayList<FirebaseUser> users) {
        super(context, layoutResourceId);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.users = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        FeedHolder holder;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        holder = new FeedHolder();
        holder.user = users.get(position);
        holder.messageButton = row.findViewById(R.id.feedMessageButton);
        holder.messageButton.setTag(holder.user);

        holder.name = row.findViewById(R.id.feedUserName);
        holder.bio = row.findViewById(R.id.feedBio);

        row.setTag(holder);

        setupItem(holder);
        return row;
    }

    private void setupItem(FeedHolder holder) {
        db = FirebaseFirestore.getInstance();
        db.collection("users").document(holder.user.getEmail()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                String firstName = task.getResult().getString("firstname");
                String lastName = task.getResult().getString("lastname");
                String bio = task.getResult().getString("bio");
                String gender = task.getResult().getString("gender");
                int age = task.getResult().getDouble("age").intValue();

                holder.name.setText(firstName);
                holder.bio.setText(bio);

            } else {
                Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class FeedHolder {
        FirebaseUser user;
        TextView name;
        TextView bio;
        Button messageButton;
    }
}
