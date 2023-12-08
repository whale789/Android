package com.example.jingnews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private NavController navController;
    private FragmentManager fmanager;
    private FragmentTransaction ftransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int resID = getResources().getIdentifier("news4", "drawable", "com.example.jingnews");
        Log.d("tttt",""+resID);
        int resID0 = getResources().getIdentifier("news21", "drawable", "com.example.jingnews");
        Log.d("tttt",""+resID0);
        /*2020.3.23
        张乾
        navactivity
         */
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

    }



    public void person(View v) {
        Log.e("jing","meun");
    }


    public void userlogin(View view) {
        Intent intent=new Intent(MainActivity.this,login.class);
        startActivity(intent);
    }



    public void gotoDownloadFragment() {    //去下载页面
        fmanager = getSupportFragmentManager();
        ftransaction = fmanager.beginTransaction();
        HistoryFragment historyFragment = new HistoryFragment();
        ftransaction.replace(R.id.fragmelayout, historyFragment);
        ftransaction.commit();
    }

    public void gotoKudosFragment(){
        fmanager=getSupportFragmentManager();
        ftransaction=fmanager.beginTransaction();
        KudosFragment kudosFragment=new KudosFragment();
        ftransaction.replace(R.id.fragmelayout,kudosFragment);
        ftransaction.commit();
    }

    public void gotoConnectionFragment(){
        fmanager=getSupportFragmentManager();
        ftransaction=fmanager.beginTransaction();
        ConnectionFragment connectionFragment=new ConnectionFragment();
        ftransaction.replace(R.id.fragmelayout,connectionFragment);
        ftransaction.commit();
    }

    @Override
    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            /*如果是自己封装的Fragment的子类  判断是否需要处理返回事件*/
            if (fragment instanceof com.example.jingnews.HistoryFragment) {
                if (((com.example.jingnews.HistoryFragment) fragment).onBackPressed()) {
                    com.google.android.material.bottomnavigation.BottomNavigationView com = findViewById(R.id.bottomNavigationView);
                    com.setVisibility(BottomNavigationView.VISIBLE);
                    super.onBackPressed();
                }
            }

            if (fragment instanceof com.example.jingnews.KudosFragment) {
                com.google.android.material.bottomnavigation.BottomNavigationView com = findViewById(R.id.bottomNavigationView);
                com.setVisibility(BottomNavigationView.VISIBLE);
                super.onBackPressed();
            }

            if(fragment instanceof com.example.jingnews.ConnectionFragment){
                com.google.android.material.bottomnavigation.BottomNavigationView com = findViewById(R.id.bottomNavigationView);
                com.setVisibility(BottomNavigationView.VISIBLE);
                super.onBackPressed();
            }

        }
        //super.onBackPressed();
    }
}