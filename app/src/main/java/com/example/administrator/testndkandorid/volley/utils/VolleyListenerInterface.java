package com.example.administrator.testndkandorid.volley.utils;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;


/**
 * 封装 Volley 请求（成功或失败）的监听事件
 * Created by Administrator on 2018/6/18 0018.
 */

public abstract class VolleyListenerInterface {
    public Context mContext;
    public static Response.Listener<String> mListener;
    public static Response.ErrorListener mErrorLister;

    public VolleyListenerInterface(Context context, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        mContext = context;
        mListener = listener;
        mErrorLister = errorListener;
    }

    // 请求成功时的回调函数
    public abstract void onMySuccesss(String result);

    // 请求失败时的回调函数
    public abstract void onMyError(VolleyError volleyError);

    // 创建请求的事件监听
    public Response.Listener<String> responseListener() {
        mListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onMySuccesss(response);
            }
        };
        return mListener;
    }

    // 创建请求失败的事件监听
    public Response.ErrorListener errorListener() {
        mErrorLister = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onMyError(error);
            }
        };
        return mErrorLister;
    }
}
