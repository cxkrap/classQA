package com.nju.classqa.util;

public interface HttpCalbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
