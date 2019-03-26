package com.redrockwork.overrdie.myimagetools.tools;

import android.graphics.Bitmap;

/**
 * 没用的类
 */
public class MyImage {
    private Bitmap bitmap;
    private String url;
    private int height = 0;
    private int width = 0;

    private MyImage (){

    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        if (bitmap!=null){
            height = bitmap.getHeight();
        }
        return height;
    }

    public int getWidth() {
        if (bitmap!=null){
            width = bitmap.getWidth();
        }
        return width;
    }

    public class Builder{
        private String url;

        public Builder setUrl(String url){
            this.url = url;
            return this;
        }

        public MyImage build(Builder builder){
            MyImage myImage = new MyImage();
            myImage.setUrl(builder.url);
            return myImage;
        }
    }
}
