package com.fullstackvalley.fragmenttest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fullstackvalley.fragmenttest.R;
import com.fullstackvalley.fragmenttest.http.beans.JokeBean;
import com.orhanobut.logger.Logger;

import java.util.List;


public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<JokeBean.ResultBean.DataBean> jokeBeans;
    private static final int LAYOUT_FOOTER = 1;
    private static final int LAYOUT_ITEM = 0;


    int footerLayoutStatus = 1;

    public int getFooterLayoutStatus() {
        return footerLayoutStatus;
    }

    public void setFooterLayoutStatus(int footerLayoutStatus) {
        this.footerLayoutStatus = footerLayoutStatus;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == LAYOUT_FOOTER) {
            Logger.i("调用footerViewHolder");
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.m_recyclerview_footer, parent, false);
            return new FooterCell(view);
        } else {
            Logger.i("调用itemViewHolder");
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cell, parent, false);
            return new MyCell(view);
        }


    }

    public HomeRecyclerViewAdapter(List<JokeBean.ResultBean.DataBean> jokeBean) {
        this.jokeBeans = jokeBean;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyCell) {
            MyCell myCell = (MyCell) holder;
            myCell.content.setText(jokeBeans.get(position).getContent());
        } else {
            FooterCell i = (FooterCell) holder;
            switch (footerLayoutStatus) {
                case 0:
                    i.tip.setText("我是有底线的!");
                    break;
                case 1:
                    i.tip.setText("正在加载");
                    break;
                case 2:
                    i.tip.setText("加载更多...");
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        int size = jokeBeans != null ? jokeBeans.size() : 0;
        Log.e(">>>>", size + "");
        return size+1;
    }

    public class MyCell extends RecyclerView.ViewHolder {
        TextView content;

        public MyCell(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }

    public class FooterCell extends RecyclerView.ViewHolder {
        TextView tip;

        public FooterCell(View itemView) {
            super(itemView);
            tip = (TextView) itemView.findViewById(R.id.tipText);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return LAYOUT_FOOTER;
        } else {
            return LAYOUT_ITEM;
        }
    }

}
