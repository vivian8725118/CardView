package com.vivian.shadedemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * *          _       _
 * *   __   _(_)_   _(_) __ _ _ __
 * *   \ \ / / \ \ / / |/ _` | '_ \
 * *    \ V /| |\ V /| | (_| | | | |
 * *     \_/ |_| \_/ |_|\__,_|_| |_|
 * <p>
 * Created by vivian on 2016/11/30.
 */

public class TopShadowView extends View {
    int width = 400;
    int height = 400;

    int x=50;
    int y=50;

    public TopShadowView(Context context) {
        super(context);
        init(context);
    }

    public TopShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TopShadowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public TopShadowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);//没有这句不显示
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width=getWidth()-x*2;
        height=getHeight()-y*2;
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        // 建立Paint 物件
        Paint paint3 = new Paint();
        paint3.setColor(Color.WHITE);
        paint3.setAntiAlias(true);
        // 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
        paint3.setShadowLayer(21, 0, 10, 0x2600A4A0);
        // 实心矩形& 其阴影
        RectF rectF = new RectF(100, 100, width, height);

        Path path=new Path();
        path.addRoundRect(x,y,width,height,new float[]{10,10,10,10,0,0,0,0}, Path.Direction.CW);
        canvas.drawPath(path, paint3);

        canvas.drawRect(x,height,width,height+21,paint);

    }

}
