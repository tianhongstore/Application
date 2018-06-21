package com.example.application;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ProgressBar;

public class MyProgressBar extends ProgressBar {

    private static final int DEFAULT_TEXT_SIZE = 10;
    private static final int DEFAULT_TEXT_COLOR = 0xfffc00d1;
    private static final int DEFAULT_UNREACH_COLOR = 0xffd3d6da;
    private static final int DEFAULT_HEIGHT = 2;
    private static final int DEFAULT_REACH_COLOR = DEFAULT_TEXT_COLOR;
    private static final int DEFAULT_TEXT_OFFSET = 10;

    private int mTextSize = sp2px(DEFAULT_TEXT_SIZE);
    private int mTextCOLOR = DEFAULT_TEXT_COLOR;
    private int mUnReachColor = DEFAULT_UNREACH_COLOR;
    private int mReachColor = DEFAULT_REACH_COLOR;
    private int mProgressHeight = dp2px(DEFAULT_HEIGHT);
    private int mTextOffSet = dp2px(DEFAULT_TEXT_OFFSET);

    private Paint mPaint;

    private int mRealWidth;

    public MyProgressBar(Context context) {
        this(context,null);
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtaionStyledAttrs(attrs);
    }

    private void obtaionStyledAttrs(AttributeSet attrs) {


        TypedArray ta=getContext().obtainStyledAttributes(
                attrs,R.styleable.MyProgressBar);
        mTextSize= (int) ta.getDimension(
                R.styleable.MyProgressBar_progress_text_size,mTextSize);
        mTextCOLOR=ta.getColor(
                R.styleable.MyProgressBar_progress_text_color,mTextCOLOR);
        mUnReachColor=ta.getColor(
                R.styleable.MyProgressBar_progress_unreach_color,mUnReachColor);
        mReachColor=ta.getColor(
                R.styleable.MyProgressBar_progress_reach_color,mReachColor);
        mProgressHeight= (int) ta.getDimension(
                R.styleable.MyProgressBar_progress_height,mProgressHeight);
        mTextOffSet= (int) ta.getDimension(
                R.styleable.MyProgressBar_progress_text_offset,mTextOffSet);
        ta.recycle();

    }

    private int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal
                , getResources().getDisplayMetrics());
    }

    private int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal
                , getResources().getDisplayMetrics());
    }

}
