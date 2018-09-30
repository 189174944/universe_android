package com.fullstackvalley.fragmenttest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fullstackvalley.fragmenttest.R;
import com.fullstackvalley.fragmenttest.login.LoginActivity;


public class Fragment3 extends Fragment {

    static Fragment3 fragment;
    View view;
    Button button;

    static public Fragment3 getInstance() {
        if (fragment == null) {
            return new Fragment3();
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment3, container, false);
        button = (Button) view.findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
