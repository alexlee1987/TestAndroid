package com.example.administrator.testndkandorid.volley.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.testndkandorid.TestApplication;

import java.util.Map;

/**
 * GET 和 POST 请求的封装
 * Created by Administrator on 2018/6/18 0018.
 */

public class VolleyRequestUtil {
    public static StringRequest sStringRequest;
    public static Context sContext;
    public static int sTimeOut = 30000;

    /**
     * 获取get请求内容
     * 参数：
     * @param context：当前上下文；
     * @param url：请求的url地址；
     * @param tag：当前请求的标签；
     * @param volleyListenerInterface：VolleyListenerInterface接口；
     * @param timeOutDefaultFlg：是否使用Volley默认连接超时；
     */
    public static void requestGet(Context context, String url, String tag, VolleyListenerInterface volleyListenerInterface,
                                  boolean timeOutDefaultFlg) {
        // 清除请求队列中的tag标记请求
        TestApplication.getHttpQueue().cancelAll(tag);
        // 创建当前的请求，获取字符串内容
        sStringRequest = new StringRequest(Request.Method.GET, url, volleyListenerInterface.responseListener(), volleyListenerInterface.errorListener());
        // 为当前请求添加标记
        sStringRequest.setTag(tag);
        // 默认超时时间以及重连次数
        int timeOut = timeOutDefaultFlg ? DefaultRetryPolicy.DEFAULT_TIMEOUT_MS : sTimeOut;
        sStringRequest.setRetryPolicy(new DefaultRetryPolicy(timeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // 将当前请求添加到请求队列中
        TestApplication.getHttpQueue().add(sStringRequest);
        // 重启当前队列
        TestApplication.getHttpQueue().start();
    }

    /**
     * 获取post请求内容
     * 参数：
     * @param context：当前上下文；
     * @param url：请求的url地址；
     * @param tag：当前请求的标签；
     * @param params：POST请求内容；
     * @param volleyListenerInterface：VolleyListenerInterface接口；
     * @param timeOutDefaultFlg：是否使用Volley默认连接超时；
     */
    public static void requestPost(Context context, String url, String tag, final Map<String, String> params, VolleyListenerInterface volleyListenerInterface,
                                   boolean timeOutDefaultFlg) {
        // 清除请求队列中的tag标记请求
        TestApplication.getHttpQueue().cancelAll(tag);
        // 创建当前的POST请求，并将请求内容参数写入Map中
        sStringRequest = new StringRequest(Request.Method.POST, url, volleyListenerInterface.responseListener(), volleyListenerInterface.errorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                return params;
            }
        };
        // 为当前请求添加标记
        sStringRequest.setTag(tag);
        // 默认超时时间以及重连次数
        int timeOut = timeOutDefaultFlg ? DefaultRetryPolicy.DEFAULT_TIMEOUT_MS : sTimeOut;
        sStringRequest.setRetryPolicy(new DefaultRetryPolicy(timeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // 将当前请求添加到请求队列中
        TestApplication.getHttpQueue().add(sStringRequest);
        // 重启当前队列
        TestApplication.getHttpQueue().start();
    }
}
