package com.fullstackvalley.fragmenttest.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

    Paint p;
    int x = 0;
    Paint p1;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setStrokeWidth(4);
        p.setAntiAlias(true);
        p.setDither(true);
        p.setColor(Color.RED);
        p1 = getTextPaint();
    }

    public Paint getTextPaint() {
        Paint p = new Paint();
        p.setTextAlign(Paint.Align.CENTER);
        p.setColor(Color.BLUE);
        p.setTextSize(40);
        p.setAntiAlias(true);
        p.setDither(true);
        return p;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
//        canvas.drawLine(0, 0, 200, 0, p);
//        canvas.rotate(10);
//        canvas.drawLine(0, 0, 200, 0, p);
//        canvas.rotate(10);
//        canvas.drawLine(0, 0, 200, 0, p);
        canvas.restore();
        canvas.drawText(x + "", 150, 150, p1);
        canvas.drawRect(0, 0, x, 50, p);
        invalidate();
        if (x < 300) {
            x++;
        } else if (x == 300) {
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(300, 300);
    }
}
