package com.fullstackvalley.fragmenttest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fullstackvalley.fragmenttest.MainActivity;
import com.fullstackvalley.fragmenttest.R;
import com.fullstackvalley.fragmenttest.adapter.ChatFriendRecyclerViewAdapter;
import com.fullstackvalley.fragmenttest.beans.Message;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment {


    static ChatFragment fragment;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ListView mListView;


    //    ArrayAdapter adapter;
    static public List<Message> dataSet = new ArrayList<>();


    static public ChatFragment getInstance() {
        if (fragment == null) {


            Message m = new Message();
            m.setNickname("昵称");
            m.setAvatar("lllll");
            List<Message.MessageItem> item = new ArrayList<>();
            Message.MessageItem messageItem = new Message.MessageItem();
            messageItem.setContent("abcdef");
            messageItem.setTime("昨天");
            item.add(messageItem);
            item.add(messageItem);
            item.add(messageItem);

            m.setMessageItems(item);

            Message m1 = new Message();
            m1.setNickname("昵称");
            m1.setAvatar("lllll");
            List<Message.MessageItem> item1 = new ArrayList<>();
            Message.MessageItem mi1 = new Message.MessageItem();
            mi1.setContent("abcdef");
            mi1.setTime("昨天");
            item1.add(mi1);
            item1.add(mi1);

            m1.setMessageItems(item1);

            dataSet.add(m);
            dataSet.add(m1);
            dataSet.add(m);
            dataSet.add(m1);
            dataSet.add(m);


            return new ChatFragment();
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        ChatFriendRecyclerViewAdapter adapter = new ChatFriendRecyclerViewAdapter(dataSet);

        linearLayoutManager = new LinearLayoutManager(container.getContext());

        DividerItemDecoration divider = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.divider));

        recyclerView.addItemDecoration(divider);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
//        setBadge(10);
//        统计小红点数量

        setBadge();
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

    public void setBadge() {
        int totalMessage = 0;
        for (int i = 0; i < dataSet.size(); i++) {
            totalMessage += dataSet.get(i).getMessageItems().size();
        }
        View view = (View) ((MainActivity) getActivity()).badge;
        TextView textView = view.findViewById(R.id.tv_msg_count);

        textView.setText(String.valueOf(totalMessage));
        if (totalMessage == 0) {
            view.setVisibility(View.INVISIBLE);
        }
    }
}
