package com.fullstackvalley.fragmenttest.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fullstackvalley.fragmenttest.ChatWindowActivity;
import com.fullstackvalley.fragmenttest.R;
import com.fullstackvalley.fragmenttest.beans.Message;

import java.util.List;

public class ChatFriendRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Message> dataSet;
    Context context;

    public ChatFriendRecyclerViewAdapter(List<Message> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_frienditem, parent, false);
        return new FriendCell(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof FriendCell) {
            final FriendCell friendCell = (FriendCell) holder;
            int number = dataSet.get(position).getMessageItems().size() > 0 ? dataSet.get(position).getMessageItems().size() : 0;
            friendCell.badge.setText(number + "");
            friendCell.myCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    延迟跳转 用户体验
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent();
                            intent.setClass(context, ChatWindowActivity.class);
                            context.startActivity(intent);
                        }
                    }, 200);

//                    Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class FriendCell extends RecyclerView.ViewHolder {
        TextView badge;
        RelativeLayout myCell;

        public FriendCell(View itemView) {
            super(itemView);
            badge = (TextView) itemView.findViewById(R.id.badge);
            myCell = (RelativeLayout) itemView.findViewById(R.id.myCell);
        }
    }
}
