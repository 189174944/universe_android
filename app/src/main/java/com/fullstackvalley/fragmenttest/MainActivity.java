package com.fullstackvalley.fragmenttest;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
    View badge;


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

        BottomNavigationView bottomNavigationView = (BottomNavigationView) this.findViewById(R.id.navigation);

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);

        BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(2);

        //加载我们的角标View，新创建的一个布局
        badge = LayoutInflater.from(this).inflate(R.layout.my_badge, menuView, false);

//添加到Tab上
        itemView.addView(badge);


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
            badge.setVisibility(View.INVISIBLE);
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
