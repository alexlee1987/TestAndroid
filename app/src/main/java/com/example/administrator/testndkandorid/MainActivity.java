package com.example.administrator.testndkandorid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.administrator.testndkandorid.volley.utils.ImageLoaderUtil;
import com.example.administrator.testndkandorid.utils.MyLib;
import com.example.administrator.testndkandorid.volley.utils.VolleyListenerInterface;
import com.example.administrator.testndkandorid.volley.utils.VolleyRequestUtil;

public class MainActivity extends AppCompatActivity {
    public String mUrl = "http://pic.58pic.com/58pic/16/62/63/97m58PICyWM_1024.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        VolleyRequestUtil.requestGet(getApplicationContext(), "https://www.baidu.com/", "baidu",
                new VolleyListenerInterface(getApplicationContext(), VolleyListenerInterface.mListener, VolleyListenerInterface.mErrorLister) {
            @Override
            public void onMySuccesss(String result) {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onMyError(VolleyError volleyError) {
                if (volleyError != null) {
                    Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, true);
    }

    public void init() {
        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        MyLib myLib = new MyLib();
        tv.setText(myLib.stringFromJNI());

        final ImageView imgView = findViewById(R.id.imgView);
        // Glide加载图片
//        GlideApp.with(this).load(mUrl).centerCrop().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imgView);
        // volley方式加载图片
        new ImageLoaderUtil().setImageRequest(mUrl, imgView);
    }
}
