package com.vivian.shadedemo.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * *          _       _
 * *   __   _(_)_   _(_) __ _ _ __
 * *   \ \ / / \ \ / / |/ _` | '_ \
 * *    \ V /| |\ V /| | (_| | | | |
 * *     \_/ |_| \_/ |_|\__,_|_| |_|
 * <p>
 * Created by vivian on 2016/12/1.
 */

public class ScreenShotUtil {
    /**
     * 截图
     *
     * @param view
     * @return
     */
    public static Bitmap convertViewToBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();  //启用DrawingCache并创建位图
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache()); //创建一个DrawingCache的拷贝，因为DrawingCache得到的位图在禁用后会被回收
        view.setDrawingCacheEnabled(false);  //禁用DrawingCahce否则会影响性能
        view.destroyDrawingCache();
        return bitmap;
    }
    /**
     * 截取长图
     *
     * @param view
     * @return
     */
    public static Bitmap getScrollViewBitmap(ViewGroup view) {
        int h = 0;
        for (int i = 0; i < view.getChildCount(); i++) {
            h += view.getChildAt(i).getHeight();
        }
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), h, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    /**
     * 保存到本地图库
     *
     * @param context
     * @param bitmap
     */
    public static void saveImageToGallery(Context context, Bitmap bitmap) {
        // Create screenshot directory if it doesn't exist
        String dirName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + File.separator + "TMT";
        File fileDir = new File(dirName);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        long mImageTime = System.currentTimeMillis();//取当前系统时间
        // media provider uses seconds for DATE_MODIFIED and DATE_ADDED, but milliseconds
        // for DATE_TAKEN
        long dateSeconds = mImageTime / 1000;
        String mImageFileName = dateSeconds + ".png"; //以保存时间命名
        String mImageFilePath = dirName + File.separator + mImageFileName; //注意这里的mImageFilePath是： 目录名称+文件名
        int mImageWidth = bitmap.getWidth();
        int mImageHeight = bitmap.getHeight();

        // Save the screenshot to the MediaStore
        ContentValues values = new ContentValues();
        ContentResolver resolver = context.getContentResolver();
        values.put(MediaStore.Images.ImageColumns.DATA, mImageFilePath);
        values.put(MediaStore.Images.ImageColumns.TITLE, mImageFileName);
        values.put(MediaStore.Images.ImageColumns.DISPLAY_NAME, mImageFileName);
        values.put(MediaStore.Images.ImageColumns.DATE_TAKEN, mImageTime);
        values.put(MediaStore.Images.ImageColumns.DATE_ADDED, dateSeconds);
        values.put(MediaStore.Images.ImageColumns.DATE_MODIFIED, dateSeconds);
        values.put(MediaStore.Images.ImageColumns.MIME_TYPE, "image/png");
        values.put(MediaStore.Images.ImageColumns.WIDTH, mImageWidth);
        values.put(MediaStore.Images.ImageColumns.HEIGHT, mImageHeight);
        Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        try {
            OutputStream out = resolver.openOutputStream(uri);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // update file size in the database
        values.clear();
        values.put(MediaStore.Images.ImageColumns.SIZE, new File(mImageFilePath).length());
        resolver.update(uri, values, null, null);
    }


}
