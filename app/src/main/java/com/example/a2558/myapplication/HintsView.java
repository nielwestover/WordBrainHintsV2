package com.example.a2558.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by a2558 on 1/12/2016.
 */

public class HintsView extends View {


    private Paint redPaint;
    private Paint blackPaint;
    public HintsView(Context context){
        super(context);

        redPaint = new Paint();
        redPaint.setAntiAlias(true);
        redPaint.setColor(Color.RED);
        blackPaint = new Paint();
        blackPaint.setAntiAlias(true);
        blackPaint.setColor(Color.BLACK);
        blackPaint.setTextAlign(Paint.Align.CENTER);
        blackPaint.setTextSize(40);

    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//
//        canvas.drawPath(RoundedRect(100,100,150, 150, 10, 10), brownPaint);
//        canvas.drawText("W", 125,125, blackPaint);
//    }
//
//    static public Path RoundedRect(float left, float top, float right, float bottom, float rx, float ry) {
//        Path path = new Path();
//        if (rx < 0) rx = 0;
//        if (ry < 0) ry = 0;
//        float width = right - left;
//        float height = bottom - top;
//        if (rx > width/2) rx = width/2;
//        if (ry > height/2) ry = height/2;
//        float widthMinusCorners = (width - (2 * rx));
//        float heightMinusCorners = (height - (2 * ry));
//
//        path.moveTo(right, top + ry);
//        path.rQuadTo(0, -ry, -rx, -ry);//top-right corner
//        path.rLineTo(-widthMinusCorners, 0);
//        path.rQuadTo(-rx, 0, -rx, ry); //top-left corner
//        path.rLineTo(0, heightMinusCorners);
//
//        path.rQuadTo(0, ry, rx, ry);//bottom-left corner
//        path.rLineTo(widthMinusCorners, 0);
//        path.rQuadTo(rx, 0, rx, -ry); //bottom-right corner
//
//
//        path.rLineTo(0, -heightMinusCorners);
//
//        path.close();//Given close, last lineto can be removed.
//
//        return path;
//    }
}
