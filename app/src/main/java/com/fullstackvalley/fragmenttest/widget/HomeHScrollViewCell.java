package com.fullstackvalley.fragmenttest.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fullstackvalley.fragmenttest.CardDetailActivity;
import com.fullstackvalley.fragmenttest.R;


public class HomeHScrollViewCell extends LinearLayout {
    public HomeHScrollViewCell(Context context) {
        super(context);
        init(context);
    }

    public HomeHScrollViewCell(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HomeHScrollViewCell(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_scrollview_cell, null);
        Button btn = (Button) view.findViewById(R.id.button);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CardDetailActivity.class);
                context.startActivity(intent);
            }
        });
        addView(view);
    }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(getMeasuredWidth(), 300);
//        this.setBackgroundResource(R.drawable.circlecorner);

    }
}
