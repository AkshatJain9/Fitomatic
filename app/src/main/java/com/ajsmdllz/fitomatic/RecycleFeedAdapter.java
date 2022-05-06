package com.ajsmdllz.fitomatic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajsmdllz.fitomatic.Posts.Post;

import java.util.List;


public class RecycleFeedAdapter extends RecyclerView.Adapter<RecycleFeedAdapter.RecycleFeedViewHolder> {
    private final Context context;
    private final List<Post> dataset;

    public RecycleFeedAdapter(Context context, List<Post> dataset){
        this.context = context;
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public RecycleFeedAdapter.RecycleFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_feed_post, parent, false);
        return new RecycleFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleFeedAdapter.RecycleFeedViewHolder holder, int position) {
        holder.getTextViewTitle().setText(dataset.get(position).getTitle());
        holder.getDescription().setText(dataset.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class RecycleFeedViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final TextView description;

        public RecycleFeedViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.individualTitleText);
            description = itemView.findViewById(R.id.individualDescriptionText);
        }

        public TextView getTextViewTitle(){return title;}
        public TextView getDescription(){return description;}
    }
}
