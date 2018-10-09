package com.fullstackvalley.fragmenttest.layout;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MyDragDeleteRelativeLayout extends RelativeLayout {
    ViewDragHelper mViewDragHelper;

    public MyDragDeleteRelativeLayout(Context context) {
        super(context);
        init();
    }

    public MyDragDeleteRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyDragDeleteRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        initDragHelper();
    }

    private void initDragHelper() {
        mViewDragHelper = ViewDragHelper.create(MyDragDeleteRelativeLayout.this, 1.0f, mDragCallback);
    }

    /**
     * ViewDragHelper回调接口
     */


    private ViewDragHelper.Callback mDragCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {//可以用来指定哪一个childView可以拖动
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {// 水平拖动
            return left;
        }
//
//        @Override
//        public int clampViewPositionVertical(View child, int top, int dy) {//竖直拖动
//            return top;
//        }
    };

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {//拦截事件
        return mViewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {//消费事件
        //将触摸事件传递给`ViewDragHelper`，必不可少
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//        }

        Log.e(">>>e", event.toString() + "");
        mViewDragHelper.processTouchEvent(event);
        return true;
    }


//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return false;
//    }
//
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return true;
//    }
}
