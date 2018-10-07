package com.fullstackvalley.fragmenttest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.fullstackvalley.fragmenttest.MainActivity;
import com.fullstackvalley.fragmenttest.R;
import com.fullstackvalley.fragmenttest.adapter.ChatFriendRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment {


    static ChatFragment fragment;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ListView mListView;


    static List<String> list = new ArrayList<>();

//    ArrayAdapter adapter;


    static public ChatFragment getInstance() {
        if (fragment == null) {
//            list.add("a");
//            list.add("a");
//            list.add("a");
//            list.add("a");
            return new ChatFragment();
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        ChatFriendRecyclerViewAdapter adapter = new ChatFriendRecyclerViewAdapter();

        linearLayoutManager = new LinearLayoutManager(container.getContext());

        DividerItemDecoration divider = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.divider));

        recyclerView.addItemDecoration(divider);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.setAdapter(adapter);

        setBadge(10);


//        mListView = view.findViewById(R.id.mListView);


//        adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, list);

//        mListView.setAdapter(adapter);
//        setBadge(list.size());

//        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        list.remove(position);
//                        adapter.notifyDataSetChanged();
//                        setBadge(list.size());
//                    }
//                });
//                builder.setMessage("确认要删除吗？");
//                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                builder.show();
//
//                return true;
//            }
//        });

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
        if (number == 0) {
            view.setVisibility(View.INVISIBLE);
        }
    }
}
