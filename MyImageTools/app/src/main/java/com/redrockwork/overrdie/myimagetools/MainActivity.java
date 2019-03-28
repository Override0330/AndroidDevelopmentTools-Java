package com.redrockwork.overrdie.myimagetools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.redrockwork.overrdie.myimagetools.cache.DoubleCacheUtils;
import com.redrockwork.overrdie.myimagetools.core.ImageLoader;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et_url);
        button = findViewById(R.id.bt_get);
        imageView = findViewById(R.id.iv_image);
        //省略了申请读写权限√
        String url = "https://pic3.zhimg.com/80/69ab924585b751cb9e7bc7b7f9f2179b_hd.jpg";
        ImageLoader.with(this)
                .from(url)
                .cacheWith(DoubleCacheUtils.getInstance())
                .into(imageView);
    }
}
