package com.example.nongsa2;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.container2);

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new Homepage();
                    fm.beginTransaction().replace(R.id.container2,fragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    fragment = new secondpage();
                    fm.beginTransaction().replace(R.id.container2,fragment).commit();
                    Log.d("ddd","MonthFragment");
                    return true;
                case R.id.navigation_notifications:
                    fragment = new thirdpage();
                    fm.beginTransaction().replace(R.id.container2,fragment).commit();
                    return true;
            }
            if(fragment!=null) {
                Log.d("ddd","setfm");
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.container2, fragment).commit();
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
