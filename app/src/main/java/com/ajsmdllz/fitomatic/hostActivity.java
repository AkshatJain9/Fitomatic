package com.ajsmdllz.fitomatic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ajsmdllz.fitomatic.Posts.PostCreationChoice;
import com.ajsmdllz.fitomatic.Posts.PostCreationGroup;
import com.ajsmdllz.fitomatic.Posts.PostCreationSingle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.ajsmdllz.fitomatic.databinding.ActivityHostBinding;

public class hostActivity extends AppCompatActivity { // COULD NEED TO EXTEND FRAGMENT ACTIVITY

    private ActivityHostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_host);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}