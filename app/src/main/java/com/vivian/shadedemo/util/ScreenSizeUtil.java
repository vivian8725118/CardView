package com.vivian.shadedemo.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class ScreenSizeUtil {

	private static DisplayMetrics metrics;

	/**
	 * 根据绝对尺寸得到相对尺寸，在不同的分辨率设备上有一致的显示效果, dip->pix
	 * 
	 * @param context
	 * @param givenAbsSize
	 *            绝对尺寸
	 * @return
	 */
	public static int getSizeByGivenAbsSize(Context context, int givenAbsSize) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, givenAbsSize, context.getResources().getDisplayMetrics());
	}

	private static DisplayMetrics getDisplayMetrics(Context context) {
		if (metrics != null) {
			return metrics;
		}
		metrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics;
	}

	public static int getScreenWidth(Context context) {
		return getDisplayMetrics(context).widthPixels; // 屏幕宽度（像素）
	}

	public static int getScreenHeight(Context context) {
		return getDisplayMetrics(context).heightPixels;// 屏幕高度（像素）
	}

	public static float getScreenDensity(Context context) {
		return getDisplayMetrics(context).density; // 屏幕密度（0.75 / 1.0 / 1.5）
	}

	public static int getScreenDensityDpi(Context context) {
		return getDisplayMetrics(context).densityDpi;// 屏幕密度DPI（120 / 160 / 240）
	}

	public static int Dp2Px(Context context, float dp) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	public static int Px2Dp(Context context, float px) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @return
	 */
	public static int px2sp(float pxValue, Context context) {
		return (int) (pxValue / context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @return
	 */
	public static int sp2px(float spValue, Context context) {
		return (int) (spValue * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
	}
}
