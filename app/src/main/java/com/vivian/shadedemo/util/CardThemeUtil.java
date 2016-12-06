package com.vivian.shadedemo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * *          _       _
 * *   __   _(_)_   _(_) __ _ _ __
 * *   \ \ / / \ \ / / |/ _` | '_ \
 * *    \ V /| |\ V /| | (_| | | | |
 * *     \_/ |_| \_/ |_|\__,_|_| |_|
 * <p>
 * Created by vivian on 2016/12/1.
 */

public class CardThemeUtil {

    public Drawable colorMatrixFilter(Context context,int res, int color) {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        //定义RGBA的矩阵
        float[] src = new float[]{
                0, 0, 0, 0, r,
                0, 0, 0, 0, g,
                0, 0, 0, 0, b,
                0, 0, 0, 1, 0};
        ColorMatrix matrix = new ColorMatrix(src);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        Drawable drawable = ContextCompat.getDrawable(context,res);
        drawable.setColorFilter(filter);
        return drawable;
    }


    /**
     * Usage:很耗性能
     * topLayout.setBackground(colorFilter(R.drawable.top));
     * centerLayout.setBackground(colorFilter(R.drawable.center));
     * bottomLayout.setBackground(colorFilter(R.drawable.bottom));
     *
     * @param res
     * @return
     */
    public Drawable colorFilter(Context context,int res) {
        Drawable drawable = context.getDrawable(res);
        Bitmap bitmap = drawableToBitmap(drawable);

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = bitmap.getPixel(x, y);
                if (color != 0xffffffff) {
                    Log.e("color:", "alpha:" + Color.alpha(color));
                    Log.e("color:", "red:" + Color.red(color));
                    Log.e("color:", "green:" + Color.green(color));
                    Log.e("color:", "blue:" + Color.blue(color) + "\n");
                    int alpha = Color.alpha(color);
                    int color2 = Color.argb(alpha, 249, 39, 54);
                    pixels[y * width + x] = color2;
                } else {
                    pixels[y * width + x] = 0xffffffff;
                }
            }
        }

        bitmap = Bitmap.createBitmap(pixels, width, height, Bitmap.Config.ARGB_4444);
        Drawable d = new BitmapDrawable(context.getResources(), bitmap);
        return d;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}
