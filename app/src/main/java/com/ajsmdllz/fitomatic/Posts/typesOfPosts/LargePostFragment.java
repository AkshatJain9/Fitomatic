package com.ajsmdllz.fitomatic.Posts.typesOfPosts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ajsmdllz.fitomatic.R;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LargePostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LargePostFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LargePostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LargePostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LargePostFragment newInstance(String param1, String param2) {
        LargePostFragment fragment = new LargePostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_large_post, container, false);
    }

    // initialize variables for multiple activity selection
    ArrayList<Integer> activityList = new ArrayList<>();
    String[] activities = {"Running", "Walking", "Weight Lifting", "Rowing", "Yoga", "Soccer", "Hiking",
                            "Gymnastics", "AFL", "Tennis", "Rugby", "Surfing", "Golf", "Bowling", "Karate",
                            "Bouldering", "Rock Climbing", "Cycling", "Mountain Biking", "Swimming",
                            "Cricket", "Judo", "Tai Quan Dao"};

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initialise variables
        TextView popUptextView = getView().findViewById(R.id.multiActivityDropdown);
        boolean[] selectedActivities = new boolean[activities.length];
        popUptextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select Activities");
                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(activities, selectedActivities, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected add in activity list
                            activityList.add(i);
                        } else {
                            // when checkbox unselected remove position from langList
                            activityList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < activityList.size(); j++) {
                            // concat array value
                            stringBuilder.append(activities[activityList.get(j)]);
                            // check condition
                            if (j != activityList.size() - 1) {
                                // add comma between elements
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        popUptextView.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedActivities.length; j++) {
                            // remove all selection
                            selectedActivities[j] = false;
                            // clear activity list
                            activityList.clear();
                            // clear text view value
                            popUptextView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });

        SeekBar priceBar = getView().findViewById(R.id.priceBar);
        TextView price = getView().findViewById(R.id.price);
        priceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                System.out.println(i);
                price.setText("Price $" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        SeekBar maxPartBar = getView().findViewById(R.id.maxPartBar);
        TextView maxPart = getView().findViewById(R.id.maxPart);
        maxPartBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                System.out.println(i);
                maxPart.setText("Max Participants " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Multiple Activity Selection
    }
}