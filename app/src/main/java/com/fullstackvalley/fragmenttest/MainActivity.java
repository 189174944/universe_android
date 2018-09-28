package com.fullstackvalley.fragmenttest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    i(R.id.myContainer, fragment1);
                    return true;
                case R.id.navigation_dashboard:
                    i(R.id.myContainer, fragment2);
                    return true;
                case R.id.navigation_notifications:
                    i(R.id.myContainer, fragment3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
//        getActionBar().getCustomView();

        fragment1 = Fragment1.getInstance();
        fragment2 = Fragment2.getInstance();
        fragment3 = Fragment3.getInstance();





        frameLayout = (FrameLayout) this.findViewById(R.id.myContainer);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        i(R.id.myContainer, fragment1);
    }

    public void i(@IdRes int containerViewId, Fragment fragment) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

}
