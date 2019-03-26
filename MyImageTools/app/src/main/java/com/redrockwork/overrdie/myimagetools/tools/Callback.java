package com.redrockwork.overrdie.myimagetools.tools;

import android.graphics.Bitmap;

public interface Callback {
    void succeed(Bitmap bitmap);
    void fail(Throwable throwable);
}
