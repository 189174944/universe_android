package com.fullstackvalley.fragmenttest;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment1 extends Fragment {

    static Fragment1 fragment;

    TextView textView;
    View view;


    static public Fragment1 getInstance() {
        if (fragment == null) {
            return new Fragment1();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String s = "啊";
        for (int i = 0; i < 100; i++) {
            s += "啊啊啊啊";
        }

        view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        textView = (TextView) view.findViewById(R.id.myText);
        textView.setTextSize(22);
        textView.setText(s);

        return view;
    }
}
