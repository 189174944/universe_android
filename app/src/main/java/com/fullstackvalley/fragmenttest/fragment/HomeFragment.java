package com.fullstackvalley.fragmenttest.fragment;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.fullstackvalley.fragmenttest.R;

import javax.security.auth.login.LoginException;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {
    @BindView(R.id.myText)
    TextView textView;

    @BindView(R.id.mScrollView)
    HorizontalScrollView mScrollView;
    long lastScrollTime;

    @OnClick(R.id.btn)
    void onClick() {
        startAnimation(textView);
    }

    static HomeFragment fragment;
    View view;
    private Unbinder unbinder;

    static public HomeFragment getInstance() {
        if (fragment == null) {
            return new HomeFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        Log.e(">>>", "视图创建");

//        textView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                    textView.offsetLeftAndRight((int)event.getX());
//                }
//
//                return true;
//            }
//        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        Log.e(">>>", "视图销毁");
    }

    @Override
    public void onResume() {
        super.onResume();
        startAnimation(textView);

        Log.e(">>>", "onResume");
        mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, final int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollX > 0) {
                    float x = scrollX * (1 / 505f);
                    if (x < 0) {
                        x = 0;
                    } else if (x > 1) {
                        x = 1;
                    }
                    textView.setAlpha(x);
                    Log.e(">>>", scrollX * (1 / 505f) + "");

                } else {
                    textView.setAlpha(0);

                }
                Log.e(">>>", scrollX + 0.0f + "");
            }

        });
    }

    public void startAnimation(final View mView) {
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 300);
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(0);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setTarget(mView);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final int mVal = (int) animation.getAnimatedValue();
                mView.getLayoutParams().width = mVal;
                mView.setLeft(mVal);
                mView.requestLayout();
            }
        });
        valueAnimator.start();
    }
}
