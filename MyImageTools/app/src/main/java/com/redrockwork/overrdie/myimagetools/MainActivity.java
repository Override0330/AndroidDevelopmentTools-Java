package com.redrockwork.overrdie.myimagetools;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.redrockwork.overrdie.myimagetools.tools.ImageNetUtil;
import com.redrockwork.overrdie.myimagetools.tools.ImageTools;

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

        ImageTools.with(this)
                .loadFromUrl("https://img-blog.csdn.net/20160127144328827?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center")
                .into(imageView);
    }
}
