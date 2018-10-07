package com.fullstackvalley.fragmenttest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fullstackvalley.fragmenttest.R;
import com.fullstackvalley.fragmenttest.beans.Message;
import com.fullstackvalley.fragmenttest.fragment.ChatFragment;

import java.util.List;

public class ChatFriendRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Message> dataSet;

    public ChatFriendRecyclerViewAdapter(List<Message> dataSet) {
        this.dataSet = dataSet;
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
            FriendCell friendCell = (FriendCell) holder;
            friendCell.index=position;
            int number = dataSet.get(position).getMessageItems().size() > 0 ? dataSet.get(position).getMessageItems().size() : 0;
            friendCell.badge.setText(number + "");
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class FriendCell extends RecyclerView.ViewHolder {
        TextView badge;
        public FriendCell(View itemView) {
            super(itemView);
            badge = (TextView) itemView.findViewById(R.id.badge);
        }
    }
}
