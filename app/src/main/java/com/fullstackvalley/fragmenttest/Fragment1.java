package com.fullstackvalley.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends Fragment {

    static Fragment1 fragment;
    View view;

    static public Fragment1 getInstance() {
        if (fragment == null) {
            return new Fragment1();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        return view;
    }
}
