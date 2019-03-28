package com.redrockwork.overrdie.myimagetools.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.redrockwork.overrdie.myimagetools.imageinterface.Callback;
import com.redrockwork.overrdie.myimagetools.imageinterface.ImageCacheUtil;
import com.redrockwork.overrdie.myimagetools.imageinterface.ImageLoad;

import java.io.InputStream;


//从文件加载图片
public class FileImage implements ImageLoad {
    private int imageId;
    private Context context;

    public FileImage(int imageId, Context context) {
        this.imageId = imageId;
        this.context = context;
    }

    @Override
    public boolean setImage(ImageCacheUtil imageCacheUtil, Callback callback) {
        Resources r = context.getResources();
        InputStream is = r.openRawResource(imageId);
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        if (bitmap!=null){
            callback.succeed(bitmap);
        }
        return false;
    }
}
