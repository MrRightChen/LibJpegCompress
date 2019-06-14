package com.chen.compress;

import android.graphics.Bitmap;

public class CompressUtils {

    static {
        System.loadLibrary("compress-lib");
    }

    private static CompressUtils mInstance;
    private CompressCallBack compressCallBack;

    public static CompressUtils getInstance(){

        if (mInstance==null){
            synchronized (CompressUtils.class){
                if (mInstance==null){
                    mInstance = new CompressUtils();
                }
            }
        }

        return mInstance;
    }

    private CompressUtils(){}

    public  native  void nativeCompress(Bitmap bitmap, int q, String path);



    /**
     * 压缩完成的回调
     */
    public void onCompleteListener(){
        if (compressCallBack!=null){
            compressCallBack.onCompleteCallBack();
        }
    }

    /**
     * 开始压缩回调
     */
    public void onStartListener(){
        if (compressCallBack!=null){
            compressCallBack.onStartCallBack();
        }
    }

    public interface CompressCallBack{

        void onCompleteCallBack();
        void onStartCallBack();
    }

    public void setCompressCallBack(CompressCallBack compressCallBack) {
        this.compressCallBack = compressCallBack;
    }
}
