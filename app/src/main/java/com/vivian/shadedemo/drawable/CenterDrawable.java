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
import android.graphics.Rect;
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
    Path path;

    Bitmap bitmap;
    int color=0x2601a3a1;

    PathEffect effects = new DashPathEffect(new float[]{ 6, 8}, 1);

    public CenterDrawable(Bitmap bitmap) {
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

        paintLine.setPathEffect(effects);
        paintLine.setStyle(Paint.Style.FILL);
        paintLine.setStrokeWidth(1);
    }

    public void setColor(int color){
        this.color=color;
        // 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
        paintShadow.setShadowLayer(21, 0, 10, color&0x26ffffff);
        invalidateSelf();
    }

    @Override
    public void onBoundsChange(Rect bounds){
        int x = bounds.left + 20;
        int y = bounds.top;

        path.reset();
        path.moveTo(x, y);
        path.lineTo(bounds.right - x, y);
        path.quadTo(bounds.right - x - bounds.bottom * 0.8f, bounds.bottom / 2, bounds.right - x, bounds.bottom);
        path.lineTo(x, bounds.bottom);
        path.quadTo(x + bounds.bottom * 0.8f, bounds.bottom / 2, x, y);
        path.close();
    }

    @Override
    public void draw(Canvas canvas) {
        int x = getBounds().left + 20;
        int y = getBounds().top;

        //画背景
        canvas.drawPath(path, paintShadow);

        //画一半虚线
        canvas.drawLine(x+getBounds().bottom*0.8f, getBounds().bottom/2, (getBounds().right - x - getBounds().bottom * 0.8f)/2-bitmap.getWidth(), getBounds().bottom/2, paintLine);

        //画引号
        canvas.drawBitmap(bitmap,getBounds().right/2-bitmap.getWidth(),y,new Paint());

        //画另一半虚线
        canvas.drawLine((getBounds().right - x - getBounds().bottom * 0.8f)/2+bitmap.getWidth()*3/2, getBounds().bottom/2,  getBounds().right - x - getBounds().bottom * 0.8f, getBounds().bottom/2, paintLine);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
