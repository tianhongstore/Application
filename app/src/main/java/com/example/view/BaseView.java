package com.example.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.example.application.R;

public abstract class BaseView extends View {

    private Context mContext;

    private Paint mPaint;


    //视图的宽高
    public int width;
    public int height;

    //起始点的坐标
    public int originalX = 100;
    public int originalY = 800;


    private String mGraphTitle;
    private String mXName;
    private String mYName;
    private float mAxisTextSize;
    private int mAxisTextColor;

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MukeGraphStyle);
        if (typedArray != null) {
            mGraphTitle = typedArray.getString(R.styleable.MukeGraphStyle_graphTitle);
            mXName = typedArray.getString(R.styleable.MukeGraphStyle_XName);
            mYName = typedArray.getString(R.styleable.MukeGraphStyle_YName);
            mAxisTextSize = typedArray.getDimension(R.styleable.MukeGraphStyle_axisTextSize, 12);
            mAxisTextColor = typedArray.getColor(R.styleable.MukeGraphStyle_axisTextColor, context.getResources().getColor(android.R.color.black));
            typedArray.recycle();
        }
        initPaint();
    }

    private void initPaint() {
        if (mPaint != null) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth() - originalX;
        height = (originalY > getHeight() ? getHeight() : originalY)-400;

        drawXAxis(canvas,mPaint);
        drawYAxis(canvas,mPaint);
        drawTitle(canvas,mPaint);
        drawXScale(canvas,mPaint);
        drawXScaleValue(canvas,mPaint);
        drawYScale(canvas,mPaint);
        drawYScaleValue(canvas,mPaint);
        drawXArrow(canvas,mPaint);
        drawYArroa(canvas,mPaint);
        drawcolumn(canvas,mPaint);
    }


    /**
     * 画柱状图
     * @param canvas
     * @param mPaint
     */


    protected abstract void drawcolumn(Canvas canvas, Paint mPaint);


    /**
     * 画Y轴的箭头
     * @param canvas
     * @param mPaint
     */
    private void drawYArroa(Canvas canvas, Paint mPaint) {

        Path pathY=new Path();
        pathY.moveTo(originalX,originalY-height-30);
        pathY.lineTo(originalX-10,originalY-height);
        pathY.lineTo(originalX+10,originalY-height);
        pathY.close();
        canvas.drawPath(pathY,mPaint);
        canvas.drawText(mYName,originalX-50,originalY-height-30,mPaint);
    }

    /**
     * 画X轴的箭头
     * @param canvas
     * @param mPaint
     */
    private void drawXArrow(Canvas canvas, Paint mPaint) {
        Path pathX=new Path();
        pathX.moveTo(originalX+width+30,originalY);
        pathX.lineTo(originalX+width+30,originalY+10);
        pathX.lineTo(originalX+width+30,originalY-10);
        pathX.close();
        canvas.drawPath(pathX,mPaint);

        canvas.drawText(mXName,originalX+width,originalY+50,mPaint);
    }


    /**
     * 画Y轴的刻度值
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawYScaleValue(Canvas canvas, Paint mPaint);


    /**
     * 画Y轴的刻度
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawYScale(Canvas canvas, Paint mPaint);


    /**
     * 画X轴的刻度值
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawXScaleValue(Canvas canvas, Paint mPaint);


    /**
     * 画X轴的刻度
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawXScale(Canvas canvas, Paint mPaint);



    /**
     * 画图标的标题
     * @param canvas
     * @param mPaint
     */
    private void drawTitle(Canvas canvas, Paint mPaint) {
        if (!TextUtils.isEmpty(mGraphTitle)){
            mPaint.setTextSize(mAxisTextSize);
            mPaint.setColor(mAxisTextColor);
            mPaint.setFakeBoldText(true);
            canvas.drawText(mGraphTitle,
                    getWidth()/2-mPaint.measureText(mGraphTitle)/2,
                    originalY+40,mPaint);
        }
    }

    /**
     * 画Y轴
     * @param canvas
     * @param mPaint
     */

    protected abstract void drawYAxis(Canvas canvas, Paint mPaint);



    /**
     * 画X轴
     * @param canvas
     * @param mPaint
     */
    protected abstract void drawXAxis(Canvas canvas, Paint mPaint);


}
