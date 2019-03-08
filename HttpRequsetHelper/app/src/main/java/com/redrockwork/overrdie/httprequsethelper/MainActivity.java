package com.redrockwork.overrdie.httprequsethelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.redrockwork.overrdie.httprequsethelper.httprequsethelper.Callback;
import com.redrockwork.overrdie.httprequsethelper.httprequsethelper.NetUtil;
import com.redrockwork.overrdie.httprequsethelper.httprequsethelper.Request;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String key [] = {"username","password"};
        String value [] = {"***","***"};
        Request request = new Request.Builder("http://bihu.jay86.com/login.php").setMethod("POST")
                                                                                    .setKeyArray(key)
                                                                                    .setValueArray(value).build();

        NetUtil.getInstance().execute(request, new Callback() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
            }

            @Override
            public void onFailed(Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
