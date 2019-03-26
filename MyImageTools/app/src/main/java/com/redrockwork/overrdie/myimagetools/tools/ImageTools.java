package com.redrockwork.overrdie.myimagetools.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.redrockwork.overrdie.myimagetools.MainActivity;

import static android.content.ContentValues.TAG;

public class ImageTools {
    Context context;
    String url = null;

    private ImageTools (Context context){
        this.context = context;
    }

    public static ImageTools with (Context context){
        return new ImageTools(context);
    }

    public ImageTools loadFromUrl(String url){
        this.url = url;
        return this;
    }

    public void into (final ImageView imageView){
        ImageNetUtil.getImageNetUtil().execute(this.url, new Callback() {
            @Override
            public void succeed(final Bitmap bitmap) {
                ((MainActivity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }

            @Override
            public void fail(Throwable throwable) {
                Log.d(TAG, "fail: "+throwable.getStackTrace());
            }
        });
    }

}
