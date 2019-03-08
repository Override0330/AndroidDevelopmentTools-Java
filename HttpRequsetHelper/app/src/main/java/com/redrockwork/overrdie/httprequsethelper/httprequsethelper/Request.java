package com.redrockwork.overrdie.httprequsethelper.httprequsethelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class Request implements Runnable {
    private String url;
    private String method;
    private int connectTimeout;
    private int readTimeout;
    private String [] key;
    private String [] value;
    private Callback callback;
    private String contentType;

    //自定义的回调接口
    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    /**
     * 建造者模式
     * @param Builder
     */

    //建造者模式下的构造器构造器
    public Request(Builder Builder){
        this.url = Builder.url;
        this.method = Builder.method;
        this.connectTimeout = Builder.connectTimeout;
        this.readTimeout = Builder.readTimeout;
        this.key = Builder.key;
        this.value = Builder.value;
        this.contentType = Builder.contentType;
    }

    //建造者模式内部类
    public static class Builder{
        private String url;
        private String method = "GET";
        private int connectTimeout = 5000;
        private int readTimeout = 5000;
        private String [] key = null;
        private String [] value = null;
        private String contentType = "application/x-www-form-urlencoded";


        public Builder(String url) {
            this.url = url;
        }

        public Builder setMethod(String method){
            this.method = method;
            return this;
        }

        public Builder setConnetTimeout(int connectTimeout){
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setReadTimeout(int readTimeout){
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder setKeyArray(String [] key){
            this.key = key;
            return this;
        }

        public Builder setValueArray(String [] value){
            this.value = value;
            return this;
        }

        public Builder setContentType(String contentType){
            this.contentType = contentType;
            return this;
        }

        public Request build(){
            return new Request(this);
        }

    }

    //网络请求逻辑
    @Override
    public void run() {
        String response = null;
        HttpURLConnection connection = null;
        try{
            URL mUrl = new URL(url);
            String data = "";
            connection = (HttpURLConnection) mUrl.openConnection();
            connection.setRequestMethod(method);
            connection.setReadTimeout(readTimeout);
            connection.setConnectTimeout(connectTimeout);
            if (method.equals("POST")){
                for (int i = 0; i < key.length; i++) {
                    data = data + key[i] + "=" + value[i];
                    if (i != key.length - 1) {
                        data = data + "&";
                    }
                }
                byte[] sendData = data.getBytes();
                int length = sendData.length;
                connection.setRequestProperty("Content-Type", contentType);
                connection.setRequestProperty("Content-Length", length + "");
                connection.setDoOutput(true);
                OutputStream out = connection.getOutputStream();
                out.write(data.getBytes());
            }
            int responseCode = connection.getResponseCode();
            if (responseCode ==200){
                InputStream is = connection.getInputStream();
                response = new JSONObject(getStringFromInputStream(is)).toString();
                callback.onResponse(response);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            callback.onFailed(e);
        } catch (IOException e) {
            e.printStackTrace();
            callback.onFailed(e);
        } catch (JSONException e) {
            e.printStackTrace();
            callback.onFailed(e);
        }finally {
            if (connection !=null){
                connection.disconnect();
            }
        }
    }





    private String getStringFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        String state = os.toString();
        os.close();
        return state;
    }
}

