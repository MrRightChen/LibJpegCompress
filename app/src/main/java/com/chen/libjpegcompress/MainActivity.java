package com.chen.libjpegcompress;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chen.compress.CompressUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;


import java.io.File;

import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {

    Bitmap beforeBitmap=null;
    private File beforeFile;
    Bitmap afterBitmap=null;
    private File afterFile;
    private CompressUtils compressUtils;
    private String TAG = "MainActivity";
    private ImageView before;
    private ImageView after;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        compressUtils = CompressUtils.getInstance();
        initView();
        initPermission();


    }

    private void initView() {

        before = (ImageView) findViewById(R.id.before);
        after = (ImageView) findViewById(R.id.after);

        compressUtils.setCompressCallBack(new CompressUtils.CompressCallBack() {
            @Override
            public void onCompleteCallBack() {
                Toast.makeText(MainActivity.this,"完成压缩",Toast.LENGTH_SHORT).show();
                File file = new File(Environment.getExternalStorageDirectory(), "test_compress.jpg");
                afterBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                Glide.with(MainActivity.this).load(afterBitmap).into(after);

            }

            @Override
            public void onStartCallBack() {

                Toast.makeText(MainActivity.this,"开始压缩",Toast.LENGTH_SHORT).show();

            }
        });






    }

//    @SuppressLint("CheckResult")
    private void initPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        Log.e(TAG,"读取文件权限："+granted);
                        flag = granted;
                        if (granted) {

                        } else {

                        }
                    }
                });
    }

    /**
     *
     * @param view
     */
    public void compress(View view) {
        if (beforeBitmap!=null){
            compressUtils.nativeCompress(beforeBitmap, 50, Environment.getExternalStorageDirectory() + "/test_compress.jpg");
        }





    }

    /**
     * 加载原图
     * @param view
     */
    public void load(View view) {

        if (flag){
            beforeFile = new File(Environment.getExternalStorageDirectory(), "test1.jpg");
            beforeBitmap = BitmapFactory.decodeFile(beforeFile.getAbsolutePath());
            Glide.with(MainActivity.this).load(beforeBitmap).into(before);
        }else{
            Toast.makeText(MainActivity.this,"请先开启权限",Toast.LENGTH_SHORT).show();

        }





    }
}
