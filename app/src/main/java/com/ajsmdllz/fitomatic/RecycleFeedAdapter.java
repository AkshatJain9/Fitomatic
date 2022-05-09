package com.ajsmdllz.fitomatic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
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
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;


public class RecycleFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<Post> dataset;

    public RecycleFeedAdapter(Context context, List<Post> dataset){
        this.context = context;
        this.dataset = dataset;
    }

    /** turns the type of post into a number where 0 -> indiv. 1 -> sml. 2 -> lrg.
     *  return -1 if the post is not any of the defined types of post
     *
     * @param position: the position in the list of the post
     * @return: the type of post as an int (0 -> indiv. 1 -> sml. 2 -> lrg.)
     */
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


    /** Inflates the layout of the Posts that is to say that it gets the right type of
     * xml file for the post.
     *
     * @param parent: yes
     * @param viewType: the type of Post that it is (0 -> indiv. 1 -> sml. 2 -> lrg.)
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0: return new IndividualViewHolder(LayoutInflater.from(context).inflate(R.layout.feed_post_individual, parent, false));
            case 1: return new SmallViewHolder(LayoutInflater.from(context).inflate(R.layout.feed_post_small_group, parent, false));
            case 2: return new LargeViewHolder(LayoutInflater.from(context).inflate(R.layout.feed_post_large_group, parent, false));
            default:
                try {
                    throw new Exception("No such type of post defined");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return null;
    }


    /** Used to populate the view holders with the things in the post held in the
     * list
     *
     * @param holder: The view holder to be populated
     * @param position: the position of the Post in the list that will populate the view
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Populate the Individual Posts
        if(dataset.get(position) instanceof SingleActivity) {
            ((IndividualViewHolder)holder).getAuthor().setText(dataset.get(position).getAuthor());
            ((IndividualViewHolder)holder).getTextViewTitle().setText(dataset.get(position).getTitle());
            ((IndividualViewHolder)holder).getDescription().setText(dataset.get(position).getDescription());
            ((IndividualViewHolder)holder).getDate().setText(dataset.get(position).getDate());
            ((IndividualViewHolder)holder).getActivity().setText(((SingleActivity) dataset.get(position)).getActivity());

            //Populate the Small Group Posts
        }else if (dataset.get(position) instanceof SmallGroupActivity){
            ((SmallViewHolder)holder).getTextViewTitle().setText(dataset.get(position).getTitle());
            ((SmallViewHolder)holder).getDescription().setText(dataset.get(position).getDescription());
            ((SmallViewHolder)holder).getDate().setText(dataset.get(position).getDate());
            ((SmallViewHolder)holder).getActivity().setText(((SmallGroupActivity) dataset.get(position)).getActivity());
            ((SmallViewHolder)holder).getLocation().setText(((SmallGroupActivity) dataset.get(position)).getLocation());

            //Populate the Large Group Posts
        } else if ((dataset.get(position) instanceof EventActivity)){
            ((LargeViewHolder)holder).getTextViewTitle().setText(dataset.get(position).getTitle());
            ((LargeViewHolder)holder).getDescription().setText(dataset.get(position).getDescription());
            ((LargeViewHolder)holder).getDate().setText(dataset.get(position).getDate());
            ((LargeViewHolder)holder).getLocation().setText(((EventActivity) dataset.get(position)).getLocation());
            ((LargeViewHolder)holder).getPrice().setText("$" + ((EventActivity) dataset.get(position)).getPrice() + "pp");

            List<String> activities = ((EventActivity) dataset.get(position)).getActivities();
            if(activities != null) {
                for (String a : activities) {
                    Chip c = new Chip(((LargeViewHolder) holder).getActivities().getContext());
                    c.setText(a);
                    c.setCheckable(false);
                    c.setBackgroundTintList(context.getResources().getColorStateList(R.color.purple));;
                }
            }
            else{
                System.out.println("SHOULD NOT GET HERE POST SHOULD HAVE ACTIVITIES");
            }
        }
    }

    /** Returns the number of items in the Recycle View
     *
     * @return: the number of items in the recycleView
     */

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
        private final TextView author;
        private final TextView title;
        private final TextView description;
        private final TextView date;
        private final Chip activity;

        public IndividualViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.individualAuthorText);
            title = itemView.findViewById(R.id.individualTitleText);
            description = itemView.findViewById(R.id.individualDescriptionText);
            date = itemView.findViewById(R.id.individualDateText);
            activity = itemView.findViewById(R.id.individualActivityChip);
        }

        public Chip getActivity() {return activity;}
        public TextView getDate() {return date;}
        public TextView getAuthor() {return author;}
        public TextView getTextViewTitle(){return title;}
        public TextView getDescription(){return description;}
    }

    public class SmallViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final TextView description;
        private final TextView date;
        private final Chip activity;
        private final TextView location;


        public SmallViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.smallTitleText);
            description = itemView.findViewById(R.id.smallDescriptionText);
            date = itemView.findViewById(R.id.smallDateText);
            activity = itemView.findViewById(R.id.smallActivityChip);
            location = itemView.findViewById(R.id.smallLocationText);
        }

        public TextView getLocation() {return location;}
        public Chip getActivity() {return activity;}
        public TextView getDate() {return date;}
        public TextView getTextViewTitle(){return title;}
        public TextView getDescription(){return description;}
    }

    public class LargeViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final TextView description;
        private final TextView date;
        private final ChipGroup activities;
        private final TextView location;
        private final TextView price;

        public LargeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.largeTitleText);
            description = itemView.findViewById(R.id.largeDescriptionText);
            date = itemView.findViewById(R.id.largeDateText);
            activities = itemView.findViewById(R.id.largeActivityChipGroup);
            location = itemView.findViewById(R.id.largeLocationText);
            price = itemView.findViewById(R.id.largePriceText);
        }

        public TextView getPrice() {return price;}
        public ChipGroup getActivities() {return activities; }
        public TextView getDate() {return date;}
        public TextView getLocation() {return location;}
        public TextView getTextViewTitle(){return title;}
        public TextView getDescription(){return description;}
    }
}
