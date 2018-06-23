package com.example.administrator.testndkandorid.utils;

/**
 * Created by Administrator on 2018/6/18 0018.
 */

public class MyLib {
    static {
        // 加载so库
        System.loadLibrary("native-lib");
    }

    public native String stringFromJNI();
}
