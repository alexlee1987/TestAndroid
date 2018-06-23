package com.example.administrator.testndkandorid.volley.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.administrator.testndkandorid.R;
import com.example.administrator.testndkandorid.TestApplication;

/**
 * 图片加载
 * Created by Administrator on 2018/6/18 0018.
 */

public class ImageLoaderUtil {

    /**
     * 通过ImageRequest来显示网络图片
     * */
    public static void setImageRequest(String url, final ImageView imageView) {
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Toast.makeText(imageView.getContext(), "success", Toast.LENGTH_LONG).show();
                imageView.setImageBitmap(response);
            }
        }, 100, 100, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) {
                    Toast.makeText(imageView.getContext(), error.networkResponse.toString(), Toast.LENGTH_LONG).show();
                }
                imageView.setBackgroundResource(R.mipmap.ic_launcher);
            }
        });

        TestApplication.getHttpQueue().add(imageRequest);
    }

    /**
     * 通过ImageLoader来显示网络图片
     * @param url
     * @param imageView
     * @param defaultImageResId
     * @param errorImageResId
     */
    public static void setImageLoader(String url, final ImageView imageView, int defaultImageResId, int errorImageResId) {
        ImageLoader imageLoader = new ImageLoader(TestApplication.getHttpQueue(), new MyImageCache());
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageView, defaultImageResId, errorImageResId);
        imageLoader.get(url, imageListener);
    }

    public static void setNetworkImageView(String url, NetworkImageView networkImageView, int defaultImageResId, int errorImageResId) {
        ImageLoader imageLoader = new ImageLoader(TestApplication.getHttpQueue(), new MyImageCache());

        networkImageView.setDefaultImageResId(defaultImageResId);
        networkImageView.setErrorImageResId(errorImageResId);
        networkImageView.setImageUrl(url, imageLoader);
    }
}
