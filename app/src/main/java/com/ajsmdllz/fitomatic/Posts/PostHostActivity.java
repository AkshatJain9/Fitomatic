package com.ajsmdllz.fitomatic.Posts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.ajsmdllz.fitomatic.R;
import com.ajsmdllz.fitomatic.databinding.ActivityPostHostBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PostHostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.ajsmdllz.fitomatic.databinding.ActivityPostHostBinding binding = ActivityPostHostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.post_nav_view);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
    }
}