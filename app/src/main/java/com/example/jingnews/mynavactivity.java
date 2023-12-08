package com.example.jingnews;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.jingnews.databinding.MynavtestBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class mynavactivity extends AppCompatActivity {
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private MynavtestBinding mynavtestBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mynavtest);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }
}
