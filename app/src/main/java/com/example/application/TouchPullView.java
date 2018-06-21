package com.example.application;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/6/7.
 */

public class TouchPullView extends View {

    //园的画笔
    private Paint mCirclePaint;
    //园的半径
    private int mCircleRadius = 200;

    private int mCirclePaintX;
    private int mCirclePaintY;

    private float mProgress;

    private int mDragHeight = 800;

    public TouchPullView(Context context) {
        super(context);
        init();
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //抗锯齿
        paint.setAntiAlias(true);
        //防抖动
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xff000000);

        mCirclePaint = paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCirclePaintX, mCirclePaintY, mCircleRadius, mCirclePaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCirclePaintX = getWidth() / 2;
        mCirclePaintY = getHeight() / 2;
    }


    //当进行测量时触发
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);


        int mixWidth = 2 * mCircleRadius + getPaddingLeft() + getPaddingRight();
        int mixHeigth = (int) ((mDragHeight * mProgress + 0.5f) + getPaddingTop() + getPaddingBottom());

        int measureWidth;
        int measureHeigth;
        //MeasureSpec.EXACTLY   具体的数值
        if (widthMode == MeasureSpec.EXACTLY) {
            measureWidth = width;

            //MeasureSpec.AT_MOST   match_parent
        } else if (widthMode == MeasureSpec.AT_MOST) {
            measureWidth = Math.min(mixWidth, width);
        } else {
            measureWidth = mixWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measureHeigth = height;

            //MeasureSpec.AT_MOST   match_parent
        } else if (heightMode == MeasureSpec.AT_MOST) {
            measureHeigth = Math.min(mixHeigth, height);
        } else {
            measureHeigth = mixHeigth;
        }

        //设置圆的宽高
        setMeasuredDimension(measureWidth, measureHeigth);
    }

    //设置手势进度
    public void setProgress(float progress) {
        mProgress = progress;
        //重新测量布局
        requestLayout();
        Logger.d("TouchPullView", "progress=" + progress);
    }
}
