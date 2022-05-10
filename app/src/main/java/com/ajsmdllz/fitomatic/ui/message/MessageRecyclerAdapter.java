package com.ajsmdllz.fitomatic.ui.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.Registration.User;
import com.google.android.material.chip.Chip;

import java.util.List;

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
        holder.getName().setText(dataset.get(position).getFirstname() + " " + dataset.get(position).getLastname());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{
        private final TextView name;
        private final Chip block;


        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.messageName);
            this.block = itemView.findViewById(R.id.messageBlock);
        }

        public Chip getBlock() {return block;}
        public TextView getName() {return name;}
    }
}
