package com.vivian.shadedemo.widget;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vivian.shadedemo.R;
import com.vivian.shadedemo.drawable.BottomDrawable;
import com.vivian.shadedemo.drawable.CenterDrawable;
import com.vivian.shadedemo.drawable.TopDrawable;
import com.vivian.shadedemo.util.ScreenSizeUtil;

/**
 * *          _       _
 * *   __   _(_)_   _(_) __ _ _ __
 * *   \ \ / / \ \ / / |/ _` | '_ \
 * *    \ V /| |\ V /| | (_| | | | |
 * *     \_/ |_| \_/ |_|\__,_|_| |_|
 * <p>
 * Created by vivian on 2016/12/1.
 */

public class CardView extends LinearLayout {
    RelativeLayout topLayout;
    ImageView centerLayout;
    TextView content;
    EditText comment;

    TopDrawable topDrawable;
    CenterDrawable centerDrawable;
    BottomDrawable bottomDrawable;

    public CardView(Context context) {
        super(context);
        init(context);
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(final Context context) {
      final  LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.card, null);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ScreenSizeUtil.Dp2Px(getContext(), 285), LayoutParams.WRAP_CONTENT));
       final int width = ScreenSizeUtil.getScreenWidth(context) - 300;
        layout.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//没有这句不显示

        topLayout = (RelativeLayout) layout.findViewById(R.id.top);
        centerLayout = (ImageView) layout.findViewById(R.id.center);
        content = (TextView) layout.findViewById(R.id.content);
        comment = (EditText) layout.findViewById(R.id.comment);

        post(new Runnable() {
            @Override
            public void run() {
                topDrawable = new TopDrawable(width, ScreenSizeUtil.Dp2Px(context, 286));
                topLayout.setBackground(topDrawable);

                centerDrawable = new CenterDrawable(width, ScreenSizeUtil.Dp2Px(context, 10), BitmapFactory.decodeResource(getResources(), R.drawable.quote));
                centerLayout.setImageDrawable(centerDrawable);

                bottomDrawable = new BottomDrawable(width, 200);
                comment.setBackground(bottomDrawable);
            }
        });

        addView(layout);
    }

    public void changeTheme(final int color){
        post(new Runnable() {
            @Override
            public void run() {
                //文字背景颜色
                GradientDrawable myGrad = (GradientDrawable)content.getBackground();
                myGrad.setColor(color);
                //顶部阴影颜色
                topDrawable.setColor(color);
                topLayout.setBackground(topDrawable);
                //中部阴影颜色
                centerDrawable.setColor(color);
                centerLayout.setBackground(centerDrawable);
                //底部阴影颜色
                bottomDrawable.setColor(color);
                comment.setBackground(bottomDrawable);
                invalidate();
            }
        });
    }

    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }
}
