package com.lazychecking.www.lazychecking.activity;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.lazychecking.www.lazychecking.R;
import com.lazychecking.www.lazychecking.activity.base.BaseActivity;
import com.lazychecking.www.lazychecking.network.HttpConstants;
import com.lazychecking.www.lazychecking.network.RequestCenter;
import com.lazychecking.www.lazychecking.okhttp.listener.DisposeDataListener;
import com.lazychecking.www.lazychecking.okhttp.request.RequestParams;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by cwl on 2017/11/23.
 */

public class CustomCamera extends BaseActivity implements SurfaceHolder.Callback,View.OnClickListener {

    private Camera mCamera;
    private SurfaceView mPreview;
    private SurfaceHolder mHolder;
    private Button mCapture;
    private RequestParams paramms;
    private byte[] picdata;
    private int mScreenWidth;
    private int mScreenHeight;
    File file;
    private Camera.PictureCallback picturecamera=new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            //Toast.makeText(CustomCamera.this, "11", Toast.LENGTH_SHORT).show();


           /* try {
                String filepath= "sdcard/temp0.jpg";
                file=new File(filepath);
                if (!file.exists()){
                    file.createNewFile();
                }
                FileOutputStream fos=new FileOutputStream(file);
                fos.write(data);

                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("info1", "onPictureTaken1: ");
            }
*/
          if (data==null)
              return;
            BufferedOutputStream bos = null;
            Bitmap bm = null;
            try {
                // 获得图片
                bm = BitmapFactory.decodeByteArray(data, 0, data.length);
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                    String filePath = "/sdcard/"+"temp.jpg";//照片保存路径
                    File file = new File(filePath);
                    if (!file.exists()){
                        file.createNewFile();
                    }
                    Log.i("info1", "Environment.getExternalStorageDirectory()="+Environment.getExternalStorageDirectory());
                    bos = new BufferedOutputStream(new FileOutputStream(file));
                    bos.write(data);
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);//将图片压缩到流中

                    paramms.put(filePath,file);
                    RequestCenter.requestUpload(HttpConstants.UPLOAD, paramms, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object object) {

                            Log.i("info1", "onSuccess: "+object.toString());
                        }

                        @Override
                        public void onFailure(Object object) {
                            Log.i("info1", "onFailure: "+object.toString());
                        }
                    });

                }else{
                   // Toast.makeText(mContext,"没有检测到内存卡", Toast.LENGTH_SHORT).show();
                    Log.i("info1", "222");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("info1", "onPictureTaken1: ");
            } finally {
                try {
                    bos.flush();//输出
                    bos.close();//关闭
                    bm.recycle();// 回收bitmap空间
                    mCamera.stopPreview();// 关闭预览
                    mCamera.startPreview();// 开启预览
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i("info1", "onPictureTaken11: ");
                }
            }

        }};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_layout);

        initView();
    }

    private void initView() {
        mCapture= (Button) findViewById(R.id.capture_btn);
        mCapture.setOnClickListener(this);
        mPreview= (SurfaceView) findViewById(R.id.surfaceView);
        mPreview.setOnClickListener(this);
        mHolder=mPreview.getHolder();
        mHolder.addCallback(this);
        paramms=new RequestParams();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera==null){
            mCamera=getCamera();
            if (mHolder!=null){
                setStartPreview(mCamera,mHolder);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    /**
     * 获取Camera对象
     * @return
     */
    private Camera getCamera(){

        try {
            mCamera=Camera.open();
        } catch (Exception e) {
            mCamera=null;
            e.printStackTrace();
        }
        return mCamera;
    }

    /**
     *预览相机内容
     */
    private void  setStartPreview(Camera camera,SurfaceHolder surfaceHolder) {

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.setDisplayOrientation(90);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 释放相机资源
     */
    private void releaseCamera(){
        if (mCamera!=null){
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera=null;
        }


    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

           setStartPreview(mCamera,mHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            mCamera.stopPreview();
        setStartPreview(mCamera,mHolder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

           releaseCamera();
    }




    Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() {

        @Override
        public void onShutter() {
            try {
               Thread.sleep(1000);//sleep 1秒钟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.capture_btn:
                Log.i("info1", "capture: ");
              Camera.Parameters parameters=mCamera.getParameters();
                parameters.setPictureFormat(ImageFormat.JPEG);
               //setCameraParams(mCamera,mScreenWidth,mScreenHeight);
                Log.i("info1", "capture11: ");
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                mCamera.takePicture(shutterCallback,null,picturecamera);/*
                mCamera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (success){
                            Log.i("info1", "suc: ");


                            Log.i("info1", "suc1: ");
                            try {
                                Log.i("info1", "suc1: ");
                                paramms.put("paper",file.getAbsoluteFile());
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            RequestCenter.requestUpload(HttpConstants.UPLOAD, paramms, new DisposeDataListener() {
                                @Override
                                public void onSuccess(Object object) {

                                }

                                @Override
                                public void onFailure(Object object) {
                                    Log.i("info", "fail: ");
                                }
                            });
                        }
                    }
                });*/
                break;
            case R.id.surfaceView:
                Log.i("info1", "onClickduijiao: ");
                mCamera.autoFocus(null);//自动对焦
                break;

            };

        }
    private void setCameraParams(Camera camera, int width, int height) {
        //Log.i(TAG,"setCameraParams  width="+width+"  height="+height);
        Camera.Parameters parameters = mCamera.getParameters();
        // 获取摄像头支持的PictureSize列表
        List<Camera.Size> pictureSizeList = parameters.getSupportedPictureSizes();
        for (Camera.Size size : pictureSizeList) {
            //Log.i(TAG, "pictureSizeList size.width=" + size.width + "  size.height=" + size.height);
        }
        /**从列表中选取合适的分辨率*/
        Camera.Size picSize = getProperSize(pictureSizeList, ((float) height / width));
        if (null == picSize) {
           // Log.i(TAG, "null == picSize");
            picSize = parameters.getPictureSize();
        }
        //Log.i(TAG, "picSize.width=" + picSize.width + "  picSize.height=" + picSize.height);
        // 根据选出的PictureSize重新设置SurfaceView大小
        float w = picSize.width;
        float h = picSize.height;
        parameters.setPictureSize(picSize.width,picSize.height);
        //this.setLayoutPara

        // 获取摄像头支持的PreviewSize列表
        List<Camera.Size> previewSizeList = parameters.getSupportedPreviewSizes();

        for (Camera.Size size : previewSizeList) {
           // Log.i(TAG, "previewSizeList size.width=" + size.width + "  size.height=" + size.height);
        }
        Camera.Size preSize = getProperSize(previewSizeList, ((float) height) / width);
        if (null != preSize) {
           // Log.i(TAG, "preSize.width=" + preSize.width + "  preSize.height=" + preSize.height);
            parameters.setPreviewSize(preSize.width, preSize.height);
        }

        parameters.setJpegQuality(100); // 设置照片质量
        if (parameters.getSupportedFocusModes().contains(android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 连续对焦模式
        }

        mCamera.cancelAutoFocus();//自动对焦。
        mCamera.setDisplayOrientation(90);// 设置PreviewDisplay的方向，效果就是将捕获的画面旋转多少度显示
        mCamera.setParameters(parameters);

    }

    private void getScreenMetrix(Context context) {
        WindowManager WM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        WM.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;
        mScreenHeight = outMetrics.heightPixels;
    }

    /**
     * 从列表中选取合适的分辨率
     * 默认w:h = 4:3
     * <p>注意：这里的w对应屏幕的height
     *            h对应屏幕的width<p/>
     */
    private Camera.Size getProperSize(List<Camera.Size> pictureSizeList, float screenRatio) {
        Log.i("info", "screenRatio=" + screenRatio);
        Camera.Size result = null;
        for (Camera.Size size : pictureSizeList) {
            float currentRatio = ((float) size.width) / size.height;
            if (currentRatio - screenRatio == 0) {
                result = size;
                break;
            }
        }

        if (null == result) {
            for (Camera.Size size : pictureSizeList) {
                float curRatio = ((float) size.width) / size.height;
                if (curRatio == 4f / 3) {// 默认w:h = 4:3
                    result = size;
                    break;
                }
            }
        }

        return result;
    }

}

