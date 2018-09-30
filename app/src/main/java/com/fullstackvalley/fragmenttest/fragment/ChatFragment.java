package com.fullstackvalley.fragmenttest.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fullstackvalley.fragmenttest.R;

public class ChatFragment extends Fragment {


    static ChatFragment fragment;
    View view;

    static public ChatFragment getInstance() {
        if (fragment == null) {
            return new ChatFragment();
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

}
