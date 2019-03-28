package com.redrockwork.overrdie.myimagetools.imageinterface;

import android.graphics.Bitmap;

/**
 * callback接口
 */
public interface Callback {
    void succeed(Bitmap bitmap);
    void fail(Throwable throwable);
}
