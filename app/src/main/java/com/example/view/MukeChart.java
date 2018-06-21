package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class MukeChart extends BaseView{
    public MukeChart(Context context) {
        this(context,null);
    }

    public MukeChart(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MukeChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void drawcolumn(Canvas canvas, Paint mPaint) {

    }

    @Override
    protected void drawYScaleValue(Canvas canvas, Paint mPaint) {

    }

    @Override
    protected void drawYScale(Canvas canvas, Paint mPaint) {

    }

    @Override
    protected void drawXScaleValue(Canvas canvas, Paint mPaint) {

    }

    @Override
    protected void drawXScale(Canvas canvas, Paint mPaint) {


    }

    @Override
    protected void drawYAxis(Canvas canvas, Paint mPaint) {

        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(6);
        canvas.drawLine(originalX,originalY,originalX,originalY-height,mPaint);
    }

    @Override
    protected void drawXAxis(Canvas canvas, Paint mPaint) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(6);
        canvas.drawLine(originalX,originalY,originalX+width,originalY,mPaint);
    }
}
