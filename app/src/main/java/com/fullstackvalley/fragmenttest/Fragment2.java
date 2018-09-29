package com.fullstackvalley.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fullstackvalley.fragmenttest.http.HttpClient;
import com.fullstackvalley.fragmenttest.http.api.UsersApi;
import com.fullstackvalley.fragmenttest.http.beans.JokeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment2 extends Fragment {

    static Fragment2 fragment;
    private RecyclerView mRecyclerView;
    private List<JokeBean.ResultBean.DataBean> jokeBean = new ArrayList<>();
    HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    int lastVisibleItem = 0;
    LinearLayoutManager linearLayoutManager;

    int page = 1;
    int pageSize = 3;
    boolean loading = true;
    boolean isRefreshing = false;

    static public Fragment2 getInstance() {
        if (fragment == null) {
            return new Fragment2();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(jokeBean);

//        swipeRefreshLayout

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isRefreshing) {
                    isRefreshing = true;
                    jokeBean.clear();
                    page=1;
                    load();
                }
            }
        });

        mRecyclerView.setAdapter(homeRecyclerViewAdapter);
        linearLayoutManager = new LinearLayoutManager(container.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&  lastVisibleItem + 1 == homeRecyclerViewAdapter.getItemCount() && !loading&&!isRefreshing) {
                    load();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
        load();
        return view;
    }

    public void load() {
        loading=true;
        UsersApi service = HttpClient.getJokeRetrofit().create(UsersApi.class);
        Call<JokeBean> call = service.getJoke2(page, pageSize);
        call.enqueue(new Callback<JokeBean>() {

            @Override
            public void onResponse(Call<JokeBean> call, Response<JokeBean> response) {
                if (response.isSuccessful()) {
                    page++;
                    Log.e(">>>>>>", "请求成功");
                    jokeBean.addAll(response.body().getResult().getData());
                    homeRecyclerViewAdapter.notifyDataSetChanged();
                    homeRecyclerViewAdapter.setFooterLayoutStatus(1);
                    loading = false;
                    if (isRefreshing) {
                        isRefreshing = false;
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<JokeBean> call, Throwable t) {
                loading=false;
                t.printStackTrace();
            }
        });
    }
}
