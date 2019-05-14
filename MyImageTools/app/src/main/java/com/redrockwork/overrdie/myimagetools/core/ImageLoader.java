package com.redrockwork.overrdie.myimagetools.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.redrockwork.overrdie.myimagetools.MainActivity;
import com.redrockwork.overrdie.myimagetools.image.FileImage;
import com.redrockwork.overrdie.myimagetools.image.UrlImage;
import com.redrockwork.overrdie.myimagetools.imageinterface.Callback;
import com.redrockwork.overrdie.myimagetools.imageinterface.ImageCacheUtil;
import com.redrockwork.overrdie.myimagetools.imageinterface.ImageLoad;

import java.util.Collections;

/**
 * 图片核心加载类
 */
public class ImageLoader {
    private Context context;
    private ImageLoad imageLoad;
    private ImageCacheUtil imageCacheUtil;

    //占位图默认值
    private static int placeHolder = android.support.compat.R.drawable.notification_icon_background;
    //错误图默认值
    private static int error = android.support.compat.R.drawable.notification_action_background;

    private ImageLoader(Context context){
        this.context = context;
    }

    /**
     * with方法来创建新的ImageLoader对象并初始化context
     * @param MainActivityContext
     * @return
     */
    public static ImageLoader with (Context MainActivityContext){
        return new ImageLoader(MainActivityContext);
    }

    /**
     * from方法进行依赖注入
     * @param url
     * @return
     */
    public ImageLoader from (String url){
        this.imageLoad = new UrlImage(url);
        return this;
    }
    public ImageLoader from (int path){
        this.imageLoad = new FileImage(path,context);
        return this;
    }

    public ImageLoader cacheWith(ImageCacheUtil imageCacheUtil){
        this.imageCacheUtil = imageCacheUtil;
        return this;
    }

    public ImageLoader placeHolder (int resource){
        placeHolder = resource;
        return this;
    }

    public ImageLoader error (int resource){
        error = resource;
        return this;
    }

    public void into (final ImageView imageView){
        //加载占位图
        ((MainActivity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageResource(placeHolder);
            }
        });
        //到这里才开始正式加载图片
        imageLoad.setImage(imageCacheUtil,new Callback() {
            @Override
            public void succeed(final Bitmap bitmap) {
                //成功
                ((MainActivity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }

            @Override
            public void fail(Throwable throwable) {
                //失败
                ((MainActivity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(error);
                    }
                });
            }
        });
    }
}
