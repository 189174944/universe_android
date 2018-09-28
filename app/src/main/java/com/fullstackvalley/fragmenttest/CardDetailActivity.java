package com.fullstackvalley.fragmenttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class CardDetailActivity extends AppCompatActivity {
    TabHost tabHost;
    TabWidget tabWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        tabHost = (TabHost) this.findViewById(R.id.myTabHost);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("asdas");
        spec.setIndicator("广场", getResources().getDrawable(R.drawable.ic_launcher, null));
        spec.setContent(R.id.tab1);
        tabHost.addTab(spec);


        TabHost.TabSpec spec1 = tabHost.newTabSpec("asdas");
        spec1.setIndicator("关注", getResources().getDrawable(R.drawable.ic_launcher, null));
        spec1.setContent(R.id.tab2);
        tabHost.addTab(spec1);

//
        tabWidget = tabHost.getTabWidget();
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
//            tabWidget.getChildAt(i).getLayoutParams().height = 88;
            tabWidget.getChildAt(i).getLayoutParams().width = 200;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
