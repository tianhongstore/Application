package com.example.application;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/6/8.
 */

public class BezierView extends View {

    private final Paint mPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
    private final Path mPath = new Path();

    public BezierView(Context context) {
        super(context);
        init();
    }


    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        Paint paint = mPaint;

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        //初始化4介曲线
        new Thread() {
            @Override
            public void run() {
                super.run();
                initBezier();
            }
        }.start();

    }

    private void initBezier() {

        float[] xPoints = new float[]{0, 300, 200, 500, 700};
        float[] yPoints = new float[]{0, 300, 700, 500, 200};

        int fps = 1000;

        for (int i = 0; i <= fps; i++) {
            float progress = i / (float) fps;
            float x = calculateBezier(progress, xPoints);
            float y = calculateBezier(progress, yPoints);
            mPath.lineTo(x, y);
            postInvalidate();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 计算贝塞尔的点所处位置
     *
     * @param t      时间
     * @param values 贝塞尔曲线的点集合
     * @return
     */

    private float calculateBezier(float t, float... values) {

        int len = values.length;

        for (int i = len - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                values[j] = values[j] + (values[j + 1] - values[j]) * t;
            }
        }
        //多介曲线的运算，曲线所在的点保存在第一个
        return values[0];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}
