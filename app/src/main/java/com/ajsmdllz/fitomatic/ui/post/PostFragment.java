package com.ajsmdllz.fitomatic.ui.post;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ajsmdllz.fitomatic.Posts.PostCreationChoice;
import com.ajsmdllz.fitomatic.Posts.PostCreationGroup;
import com.ajsmdllz.fitomatic.Posts.PostCreationSingle;
import com.ajsmdllz.fitomatic.R;


public class PostFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_post, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button createSinglePost = getView().findViewById(R.id.createSingleButton);
        Button createGroupPost = getView().findViewById(R.id.createGroupButton);

        // createSinglePost Button handler
        createSinglePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PostCreationSingle.class));
            }
        });

        // createGroupPost Button handler
        createGroupPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PostCreationGroup.class));
            }
        });
    }
}