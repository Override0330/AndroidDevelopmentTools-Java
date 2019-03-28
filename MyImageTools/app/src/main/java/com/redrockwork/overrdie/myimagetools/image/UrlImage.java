package com.redrockwork.overrdie.myimagetools.image;

import android.graphics.Bitmap;

import com.redrockwork.overrdie.myimagetools.ThreadPool.ThreadPoolWithDCL;
import com.redrockwork.overrdie.myimagetools.imageinterface.Callback;
import com.redrockwork.overrdie.myimagetools.imageinterface.ImageCacheUtil;
import com.redrockwork.overrdie.myimagetools.imageinterface.ImageLoad;
import com.redrockwork.overrdie.myimagetools.other.MD5Util;


//从URL加载图片
public class UrlImage implements ImageLoad {
    private String url;

    public UrlImage(String url) {
        this.url = url;
    }

    @Override
    public boolean setImage(ImageCacheUtil imageCacheUtil, Callback callback) {
        String key = MD5Util.crypt(url);
        final Bitmap[] image = {imageCacheUtil.getBitmapFromCache(key)};
        if (image[0] != null) {
            callback.succeed(image[0]);
            return true;
        }
        ThreadPoolWithDCL.getInstance().execute(url, callback, imageCacheUtil);
        return true;
    }

}
