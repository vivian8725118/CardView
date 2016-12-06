package com.vivian.shadedemo.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
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

public class TopDrawable extends Drawable {
    Paint paint;
    Paint paintShadow;
    Path path;
    RectF rectF;
    int x = 20;
    int y = 20;
    int color = 0x2601a3a1;

    public TopDrawable() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);

        paintShadow = new Paint();
        paintShadow.setColor(Color.WHITE);
        paintShadow.setAntiAlias(true);

        path = new Path();
    }

    public void setColor(int color) {
        this.color = color;
        // 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
        paintShadow.setShadowLayer(21, 0, 10, color & 0x26ffffff);//0x2600A4A0
        invalidateSelf();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(path, paintShadow);
        canvas.drawRect(x, getBounds().height(), getBounds().width() - x, getBounds().height() + 21, paint);
    }

    @Override
    public void onBoundsChange(Rect bounds) {
        path.reset();
        rectF = new RectF(x, y, bounds.width() - x, bounds.height());
        path.addRoundRect(rectF, new float[]{6, 6, 6, 6, 0, 0, 0, 0}, Path.Direction.CW);
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
