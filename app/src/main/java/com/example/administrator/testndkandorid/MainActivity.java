package com.example.administrator.testndkandorid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.testndkandorid.volley.utils.ImageLoaderUtil;
import com.example.administrator.testndkandorid.utils.MyLib;
import com.example.administrator.testndkandorid.volley.utils.VolleyListenerInterface;
import com.example.administrator.testndkandorid.volley.utils.VolleyRequestUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
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
    }

    public void init() {
        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        MyLib myLib = new MyLib();
        tv.setText("JNI请求结果：" + myLib.stringFromJNI());

        final ImageView imgView = findViewById(R.id.imgView);
        // Glide加载图片
//        GlideApp.with(this).load(mUrl).centerCrop().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imgView);
        // volley方式加载图片
        new ImageLoaderUtil().setImageRequest(mUrl, imgView);
        findViewById(R.id.volley_request).setOnClickListener(this);
    }

    private void requestVolley() {
        VolleyRequestUtil.requestGet(getApplicationContext(), "https://www.baidu.com/", "baidu",
                new VolleyListenerInterface(getApplicationContext(), VolleyListenerInterface.mListener, VolleyListenerInterface.mErrorLister) {
                    @Override
                    public void onMySuccesss(String result) {
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
                        Log.i("lgdd", "onMysuccess res: " + result);
                    }

                    @Override
                    public void onMyError(VolleyError volleyError) {
                        if (volleyError != null) {
                            Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                            Log.i("lgdd", "onMyError res: " + volleyError.toString());
                        }
                    }
                }, true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.volley_request:
                requestVolley();
                break;
        }
    }
}
