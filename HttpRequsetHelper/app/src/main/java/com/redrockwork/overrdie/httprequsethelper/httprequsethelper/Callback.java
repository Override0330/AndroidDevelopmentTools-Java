package com.redrockwork.overrdie.httprequsethelper.httprequsethelper;

public interface Callback {
    void onResponse(String response);
    void onFailed(Throwable t);
}
