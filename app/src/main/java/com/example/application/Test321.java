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

public class Test321 extends View {

    private final Paint mPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
    private final Path mPath = new Path();


    public Test321(Context context) {
        super(context);
        init();
    }


    public Test321(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Test321(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {

        Paint paint = mPaint;

        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);


        //一阶曲线，就是一条线
        Path path = mPath;
        path.moveTo(100, 100);
        path.lineTo(400, 400);


        //二阶曲线，控制点坐标-终点坐标   --坐标是坐标轴中的绝对位置
        path.quadTo(600, 100, 800, 400);
        //二阶曲线，控制点坐标-终点坐标   --坐标是相对path最后所在的位置
//        path.rQuadTo(500, 100, 700, 300);

        path.moveTo(400, 800);

        //三阶曲线      两个控制点坐标，最后一个是终点
        //三阶曲线也有相对坐标方法
        //path.rCubicTo(,,,,,);
        path.cubicTo(500, 600, 700, 1200, 800, 800);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
        canvas.drawPoint(500, 0, mPaint);

        canvas.drawPoint(500, 600, mPaint);
        canvas.drawPoint(700, 1200, mPaint);
    }
}
