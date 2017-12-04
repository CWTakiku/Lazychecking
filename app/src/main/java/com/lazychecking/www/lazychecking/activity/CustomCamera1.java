package com.lazychecking.www.lazychecking.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.lazychecking.www.lazychecking.R;
import com.lazychecking.www.lazychecking.activity.base.BaseActivity;
import com.lazychecking.www.lazychecking.view.fragment.surfaceview.CameraSurfaceView;
import com.lazychecking.www.lazychecking.view.fragment.surfaceview.RectOnCamera;

public class CustomCamera1 extends BaseActivity implements View.OnClickListener,RectOnCamera.IAutoFocus{

    private CameraSurfaceView mCameraSurfaceView;
   private RectOnCamera mRectOnCamera;
    private Button takePicBtn;

    private boolean isClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mCameraSurfaceView = (CameraSurfaceView) findViewById(R.id.cameraSurfaceView);
        mRectOnCamera = (RectOnCamera) findViewById(R.id.rectOnCamera);
        takePicBtn= (Button) findViewById(R.id.takePic);
        mRectOnCamera.setIAutoFocus(this);
        takePicBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.takePic:
                //mCameraSurfaceView.setAutoFocus();
                mCameraSurfaceView.takePicture();
                //mCameraSurfaceView.onAutoFocus(mCameraSurfaceView.getCamera());

                break;
            default:
                break;
        }
    }
    @Override
    public void autoFocus() {
        mCameraSurfaceView.setAutoFocus();
    }


}