package com.vivian.shadedemo.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * *          _       _
 * *   __   _(_)_   _(_) __ _ _ __
 * *   \ \ / / \ \ / / |/ _` | '_ \
 * *    \ V /| |\ V /| | (_| | | | |
 * *     \_/ |_| \_/ |_|\__,_|_| |_|
 * <p>
 * Created by vivian on 2016/11/30.
 */

public class CenterDrawable extends Drawable {
    Paint paint;
    Paint paintShadow;
    Paint paintLine;
    int right = 400;
    int bottom = 400;
    Path path;
    int x = 20;
    int y = 0;

    Bitmap bitmap;
    int color=0x2601a3a1;

    public CenterDrawable(int right, int height,Bitmap bitmap) {
        this.right = right;
        this.bottom = height;
        this.bitmap=bitmap;

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);

        paintShadow = new Paint();
        paintShadow.setColor(Color.WHITE);
        paintShadow.setAntiAlias(true);

        path = new Path();

        paintLine = new Paint();
        paintLine.setColor(Color.GRAY);
        paintLine.setAntiAlias(true);
    }

    public void setColor(int color){
        this.color=color;
    }

    @Override
    public void draw(Canvas canvas) {
        // 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
        paintShadow.setShadowLayer(21, 0, 10, color&0x26ffffff);
        //画背景
        path.moveTo(x, y);
        path.lineTo(right - x, y);
        path.quadTo(right - x - bottom * 0.8f, bottom / 2, right - x, bottom);
        path.lineTo(x, bottom);
        path.quadTo(x + bottom * 0.8f, bottom / 2, x, y);
        path.close();
        canvas.drawPath(path, paintShadow);

        //画一半虚线
        PathEffect effects = new DashPathEffect(new float[]{ 6, 8}, 1);
        paintLine.setPathEffect(effects);
        paintLine.setStyle(Paint.Style.FILL);
        paintLine.setStrokeWidth(1);
        canvas.drawLine(x+bottom*0.8f, bottom/2, (right - x - bottom * 0.8f)/2-bitmap.getWidth(), bottom/2, paintLine);

        //画引号
        canvas.drawBitmap(bitmap,right/2-bitmap.getWidth(),y,new Paint());

        //画另一半虚线
        canvas.drawLine((right - x - bottom * 0.8f)/2+bitmap.getWidth()*3/2, bottom/2,  right - x - bottom * 0.8f, bottom/2, paintLine);
    }

    public void setRight(int right) {
        this.right = right;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override
    public int getIntrinsicHeight() {
        return bottom;
    }

    @Override
    public int getIntrinsicWidth() {
        return right;
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
