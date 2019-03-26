package com.redrockwork.overrdie.myimagetools.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

/**
 *
 */
public class ImageNetUtil {
    /**
     *DCL,double-checked locking模式的单例模式(双重校验锁)
     * 实现了懒加载以及多线程安全
     */
    private volatile static ImageNetUtil imageNetUtil;
    public static ImageNetUtil getImageNetUtil(){
        if (imageNetUtil == null){
            synchronized (ImageNetUtil.class){
                if (imageNetUtil == null){
                    imageNetUtil = new ImageNetUtil();
                }
            }
        }
        return imageNetUtil;
    }

    //因为图片加载通常需要较长的时间所以我们使用核心线程池来进行√

    private int corePoolSize;
    private int maxPoolSize;
    private long keepAliveTime = 30;
    private TimeUnit timeUnit = TimeUnit.MINUTES;
    private ThreadPoolExecutor executor;

    private ImageNetUtil(){
        corePoolSize = Runtime.getRuntime().availableProcessors()*2+1;
        maxPoolSize = 30;
        executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                timeUnit,
                new LinkedBlockingDeque<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public void execute(final String url, final Callback callback){
        Log.d(TAG, "开始从网络加载图片  url:"+url);
        final HttpURLConnection[] connection = {null};
        final URL[] mUrl = new URL[1];

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    mUrl[0] = new URL(url);
                    connection[0] = (HttpURLConnection) mUrl[0].openConnection();
                    //设置图片加载时长为15s
                    connection[0].setConnectTimeout(15000);
                    int responseCode = connection[0].getResponseCode();
                    if (responseCode == 200){
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 1;
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        InputStream inputStream = connection[0].getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream,null,options);
                        inputStream.close();
                        callback.succeed(bitmap);
                    }
                }catch (MalformedURLException e) {
                    e.printStackTrace();
                    callback.fail(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.fail(e);
                }finally {
                    if (connection[0] != null){
                        connection[0].disconnect();
                    }
                }
            }
        });

    }
}
