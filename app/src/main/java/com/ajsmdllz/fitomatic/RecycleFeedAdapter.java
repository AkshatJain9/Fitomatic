package com.ajsmdllz.fitomatic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajsmdllz.fitomatic.Posts.EventActivity;
import com.ajsmdllz.fitomatic.Posts.Post;
import com.ajsmdllz.fitomatic.Posts.SingleActivity;
import com.ajsmdllz.fitomatic.Posts.SmallGroupActivity;

import java.util.List;


public class RecycleFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<Post> dataset;

    public RecycleFeedAdapter(Context context, List<Post> dataset){
        this.context = context;
        this.dataset = dataset;
    }

    @Override
    public int getItemViewType(int position) {
        if(dataset.get(position) instanceof SingleActivity) {
            return 0;
        }else if (dataset.get(position) instanceof SmallGroupActivity){
            return 1;
        } else if ((dataset.get(position) instanceof EventActivity)){
            return 2;
        }
        return -1;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0: return new IndividualViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_feed_post, parent, false));
            case 1: return new SmallViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_feed_post, parent, false));
            case 2: return new LargeViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_feed_post, parent, false));
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
        if(dataset.get(position) instanceof SingleActivity) {
            ((IndividualViewHolder)holder).getTextViewTitle().setText(dataset.get(position).getTitle());
            ((IndividualViewHolder)holder).getDescription().setText(dataset.get(position).getDescription());
        }else if (dataset.get(position) instanceof SmallGroupActivity){
            ((SmallViewHolder)holder).getTextViewTitle().setText(dataset.get(position).getTitle());
            ((SmallViewHolder)holder).getDescription().setText(dataset.get(position).getDescription());
        } else if ((dataset.get(position) instanceof EventActivity)){
            ((LargeViewHolder)holder).getTextViewTitle().setText(dataset.get(position).getTitle());
            ((LargeViewHolder)holder).getDescription().setText(dataset.get(position).getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    /**
     *  Define the View holders for each of the types of posts.
     *  They are how the post itself is displayed (they define the xml file used)
     *  on the recycle view
     */

    public class IndividualViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final TextView description;

        public IndividualViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.individualTitleText);
            description = itemView.findViewById(R.id.individualDescriptionText);
        }

        public TextView getTextViewTitle(){return title;}
        public TextView getDescription(){return description;}
    }

    public class SmallViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final TextView description;

        public SmallViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.individualTitleText);
            description = itemView.findViewById(R.id.individualDescriptionText);
        }

        public TextView getTextViewTitle(){return title;}
        public TextView getDescription(){return description;}
    }

    public class LargeViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final TextView description;

        public LargeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.individualTitleText);
            description = itemView.findViewById(R.id.individualDescriptionText);
        }

        public TextView getTextViewTitle(){return title;}
        public TextView getDescription(){return description;}
    }
}
