package com.example.a2558.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
/**
 * Created by a2558 on 1/16/2016.
 */
public class SingleWordView extends View  {
    Paint redPaint = null;
    Paint blackPaint = null;
    Paint creamPaint = null;
    public SingleWordView(Context context, AttributeSet attrs){
        super(context, attrs);

        setWillNotDraw(false);
        redPaint = new Paint();
        redPaint.setAntiAlias(true);
        redPaint.setColor(Color.parseColor("#C27C37"));
        redPaint.setStyle(Paint.Style.STROKE);
        creamPaint = new Paint();
        creamPaint.setAntiAlias(true);
        creamPaint.setColor(Color.parseColor("#EFE8BE"));
        blackPaint = new Paint();
        blackPaint.setAntiAlias(true);
        blackPaint.setColor(Color.BLACK);
        blackPaint.setTextAlign(Paint.Align.CENTER);
        blackPaint.setTextSize(DeviceDimensionsHelper.convertDpToPixel(18, getContext()));
    }

    private String answer = "";
    public void setAnswer(String ans){
        answer = ans;
        //this.invalidate();
    }

    private int hintsRequested = 0;
    public void requestHint(){
        hintsRequested++;
        if (hintsRequested > answer.length())
            hintsRequested--;
        this.invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        float startX = DeviceDimensionsHelper.convertDpToPixel(5, getContext());
        float startY = DeviceDimensionsHelper.convertDpToPixel(5, getContext());
        float width = DeviceDimensionsHelper.convertDpToPixel(30, getContext());
        float gap = DeviceDimensionsHelper.convertDpToPixel(5, getContext());
        float totalWidth = width + gap;
        float rounding = DeviceDimensionsHelper.convertDpToPixel(5, getContext());
        float yOffset = DeviceDimensionsHelper.convertDpToPixel(21, getContext());
        for (int i = 0; i < answer.length(); ++i){
            if(i < hintsRequested) {
                canvas.drawPath(RoundedRect(startX + i * totalWidth, startY, startX + i * totalWidth + width, startY + width, rounding, rounding), creamPaint);
                canvas.drawText(answer.toUpperCase().substring(i, i + 1), startX + i * totalWidth + width/2, startY + yOffset, blackPaint);
            }
            else {
                canvas.drawPath(RoundedRect(startX + i * totalWidth, startY, startX + i * totalWidth + width, startY + width, rounding, rounding), redPaint);
            }
        }
    }

    static public Path RoundedRect(float left, float top, float right, float bottom, float rx, float ry) {
        Path path = new Path();
        if (rx < 0) rx = 0;
        if (ry < 0) ry = 0;
        float width = right - left;
        float height = bottom - top;
        if (rx > width/2) rx = width/2;
        if (ry > height/2) ry = height/2;
        float widthMinusCorners = (width - (2 * rx));
        float heightMinusCorners = (height - (2 * ry));

        path.moveTo(right, top + ry);
        path.rQuadTo(0, -ry, -rx, -ry);//top-right corner
        path.rLineTo(-widthMinusCorners, 0);
        path.rQuadTo(-rx, 0, -rx, ry); //top-left corner
        path.rLineTo(0, heightMinusCorners);

        path.rQuadTo(0, ry, rx, ry);//bottom-left corner
        path.rLineTo(widthMinusCorners, 0);
        path.rQuadTo(rx, 0, rx, -ry); //bottom-right corner


        path.rLineTo(0, -heightMinusCorners);

        path.close();//Given close, last lineto can be removed.

        return path;

    }
}
