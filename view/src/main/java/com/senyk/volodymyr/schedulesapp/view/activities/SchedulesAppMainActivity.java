package com.senyk.volodymyr.schedulesapp.view.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.senyk.volodymyr.schedulesapp.R;

public class SchedulesAppMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavController navController = Navigation.findNavController(this, R.id.host_fragment);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNavigation, navController);
    }
}
