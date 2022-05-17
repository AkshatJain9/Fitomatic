package com.ajsmdllz.fitomatic.ui.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajsmdllz.fitomatic.P2PMessaging.Message;
import com.ajsmdllz.fitomatic.Posts.Post;
import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.RecycleFeedAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Objects;

public class DirectMessageRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final Context context;
    private final List<Message> dataset;

    public DirectMessageRecyclerAdapter(Context context, List<Message> dataset){
        this.context = context;
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0: return new myMessageHolder(LayoutInflater.from(context).inflate(R.layout.direct_message_my, parent, false));
            case 1: return new theirMessageHolder(LayoutInflater.from(context).inflate(R.layout.direct_message_theirs, parent, false));
            default:
                try {
                    throw new Exception("No such type of post defined");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if(dataset.get(position).getSender().equals(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail())){
            ((myMessageHolder) holder).getMessage().setText(dataset.get(position).getMessage());
        }
        else{
            ((theirMessageHolder) holder).getMessage().setText(dataset.get(position).getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (dataset.get(position).getSender().equals(mAuth.getCurrentUser().getEmail()))
            return 0;
        else
            return 1;
    }

    public static class myMessageHolder extends RecyclerView.ViewHolder{
        TextView message;

        public myMessageHolder(@NonNull View itemView) {
            super(itemView);
            this.message = itemView.findViewById(R.id.my_message_text);
        }

        public TextView getMessage() {
            return message;
        }
    }

    public static class theirMessageHolder extends RecyclerView.ViewHolder{
        TextView message;
        public theirMessageHolder(@NonNull View itemView) {
            super(itemView);
            this.message = itemView.findViewById(R.id.their_message_text);
        }
        public TextView getMessage() {
            return message;
        }
    }

}
