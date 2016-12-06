package com.vivian.shadedemo.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.vivian.shadedemo.R;

/**
 * *          _       _
 * *   __   _(_)_   _(_) __ _ _ __
 * *   \ \ / / \ \ / / |/ _` | '_ \
 * *    \ V /| |\ V /| | (_| | | | |
 * *     \_/ |_| \_/ |_|\__,_|_| |_|
 * <p>
 * Created by vivian on 2016/11/30.
 */

public class CircleShadowDrawable extends Drawable {
    private Bitmap bitmap = null;
    Context mContext;
    // 建立Paint 物件
    Paint paint3 = new Paint();

    public CircleShadowDrawable(Context context){
        mContext=context;
//        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.top_selector);
    }
    @Override
    public void draw(Canvas canvas) {
        paint3.setColor(Color.RED);
        // 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
        paint3.setShadowLayer(300, 0, 0, Color.RED);
        // 实心矩形& 其阴影
        canvas.drawCircle(400, 500, 300, paint3);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
