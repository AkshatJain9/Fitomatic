package com.ajsmdllz.fitomatic.ui.message;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajsmdllz.fitomatic.P2PMessaging.Message;
import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.Registration.User;
import com.google.android.material.chip.Chip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.MessageViewHolder> {
    private final Context context;
    private final List<User> dataset;

    public MessageRecyclerAdapter(Context context, List<User> dataset){
        this.dataset = dataset;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageRecyclerAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_recycler, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageRecyclerAdapter.MessageViewHolder holder, int position) {
        holder.email = dataset.get(position).getEmail();
        holder.getName().setText(dataset.get(position).getFirstname() + " " + dataset.get(position).getLastname());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{
        FirebaseFirestore db;
        String email;
        private final TextView name;
        private final Chip block;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            db = FirebaseFirestore.getInstance();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            this.name = itemView.findViewById(R.id.messageName);
            this.block = itemView.findViewById(R.id.messageBlock);
            // Block button handler
            this.block.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.collection("users").document(Objects.requireNonNull(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail())).get().addOnCompleteListener(task -> {
                        if (task.isSuccessful() && task.getResult() != null) {
                            ArrayList<String> blocked = task.getResult().get("blocked", ArrayList.class);
                            Toast.makeText(context, email, Toast.LENGTH_SHORT).show();
                            if (blocked != null && !blocked.contains(email)) {
                                // Adding the post to the user's list of blocked users
                                blocked.add(email);
                                db.collection("users").document(Objects.requireNonNull(mAuth.getCurrentUser().getEmail())).update("blocked", blocked);
                            } else {
                                Toast.makeText(context, "Already blocked this user!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

            this.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String recip = email;
                    Intent intent = new Intent(context, DirectMessage.class);
                    intent.putExtra("recip", recip);
                    context.startActivity(intent);
                }
            });

        }

        public Chip getBlock() {return block;}
        public String getEmail() {return email;}
        public TextView getName() {return name;}
    }
}
