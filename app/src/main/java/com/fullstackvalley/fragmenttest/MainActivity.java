package com.fullstackvalley.fragmenttest;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.fullstackvalley.fragmenttest.fragment.ChatFragment;
import com.fullstackvalley.fragmenttest.fragment.Fragment2;
import com.fullstackvalley.fragmenttest.fragment.Fragment3;
import com.fullstackvalley.fragmenttest.fragment.HomeFragment;
import com.fullstackvalley.fragmenttest.helpers.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    HomeFragment homeFragment;
    ChatFragment chatFragment;
    Fragment2 fragment2;
    Fragment3 fragment3;
    long exitTime = 0;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    i(R.id.myContainer, homeFragment);
                    return true;
                case R.id.navigation_dashboard:
                    i(R.id.myContainer, fragment2);
                    return true;
                case R.id.im:
                    i(R.id.myContainer, chatFragment);
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
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
//        getActionBar().getCustomView();

        homeFragment = HomeFragment.getInstance();
        fragment2 = Fragment2.getInstance();
        fragment3 = Fragment3.getInstance();
        chatFragment = ChatFragment.getInstance();


//        try {
//            File file = new File(getCacheDir()+File.separator + "abc");
//            File file1 = new File(getFilesDir()+File.separator+"myfile.txt");
//            file1.createNewFile();
//            if (file1.exists()){
//            }
//            FileOutputStream fileOutputStream = new FileOutputStream(getCacheDir()+File.separator + "abcd");
//            fileOutputStream.write("abcdef".getBytes());
//            fileOutputStream.close();
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        frameLayout = (FrameLayout) this.findViewById(R.id.myContainer);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        反射修改属性
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        i(R.id.myContainer, homeFragment);
    }

    public void i(@IdRes int containerViewId, Fragment fragment) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }


}
