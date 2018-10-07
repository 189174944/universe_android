package com.fullstackvalley.fragmenttest.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fullstackvalley.fragmenttest.MainActivity;
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

        Activity activity = (MainActivity) getActivity();
        View view = (View) ((MainActivity) activity).badge;
        TextView textView = view.findViewById(R.id.tv_msg_count);
        textView.setText(String.valueOf(1000));
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

}
