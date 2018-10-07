package com.fullstackvalley.fragmenttest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fullstackvalley.fragmenttest.adapter.HomeRecyclerViewAdapter;
import com.fullstackvalley.fragmenttest.MainActivity;
import com.fullstackvalley.fragmenttest.R;
import com.fullstackvalley.fragmenttest.http.HttpClient;
import com.fullstackvalley.fragmenttest.http.api.UsersApi;
import com.fullstackvalley.fragmenttest.http.beans.JokeBean;
import com.fullstackvalley.fragmenttest.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment2 extends Fragment {

    static Fragment2 fragment;
    private Context context;
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        context = container.getContext();

        if (!Utils.isNetWorkAvailable(container.getContext())){
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.network_disable, container, false);
            TextView refresh = (TextView) view.findViewById(R.id.refresh);
            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//
//                    android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.myContainer, fragment);
//                    fragmentTransaction.commit();

                    MainActivity mainActivity = (MainActivity)getActivity();
                    mainActivity.i(R.id.myContainer, Fragment2.getInstance());

//                    重启Activity
//                    Intent intent = getIntent();
//                    overridePendingTransition(0, 0);
//                    finish();
//                    overridePendingTransition(0, 0);
//                    startActivity(intent);
//                    重启Activity
                }
            });
            return view;
        }


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

    @Override
    public void onResume() {
        super.onResume();
    }

    public void load() {

        if (!Utils.isNetWorkAvailable(context)){
            MainActivity mainActivity = (MainActivity)getActivity();
            mainActivity.i(R.id.myContainer, Fragment2.getInstance());
            return;
        }
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
