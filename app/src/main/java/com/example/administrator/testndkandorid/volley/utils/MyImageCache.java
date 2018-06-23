package com.example.administrator.testndkandorid.volley.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Administrator on 2018/6/18 0018.
 */

public class MyImageCache implements ImageLoader.ImageCache{
    private LruCache<String,Bitmap> myCache;

    public MyImageCache(){
        myCache = new LruCache<String,Bitmap>((int)(Runtime.getRuntime().maxMemory()/1024/8)){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }
    //从缓存中返回位图
    @Override
    public Bitmap getBitmap(String s) {
        return myCache.get(s);
    }
    //将图片放入缓存
    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        myCache.put(s,bitmap);
    }
}
