package com.redrockwork.overrdie.myimagetools.imageinterface;

import android.graphics.Bitmap;

public interface ImageCacheUtil {
    void setBitmapToCache(String key, Bitmap bitmap);
    Bitmap getBitmapFromCache(String url);
}
