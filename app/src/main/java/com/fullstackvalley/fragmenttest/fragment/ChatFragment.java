package com.fullstackvalley.fragmenttest.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fullstackvalley.fragmenttest.MainActivity;
import com.fullstackvalley.fragmenttest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemLongClick;

public class ChatFragment extends Fragment {


    static ChatFragment fragment;
    View view;

    ListView mListView;


    static List<String> list = new ArrayList<>();

    ArrayAdapter adapter;


    static public ChatFragment getInstance() {
        if (fragment == null) {
            list.add("a");
            list.add("a");
            list.add("a");
            list.add("a");
            return new ChatFragment();
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        mListView = view.findViewById(R.id.mListView);


        adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, list);

        mListView.setAdapter(adapter);
        setBadge(list.size());

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        setBadge(list.size());
                    }
                });
                builder.setMessage("确认要删除吗？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

                return true;
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setBadge(int number) {
        View view = (View) ((MainActivity) getActivity()).badge;
        TextView textView = view.findViewById(R.id.tv_msg_count);
        textView.setText(String.valueOf(number));
    }
}
