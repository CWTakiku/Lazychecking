package com.lazychecking.www.lazychecking.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.lazychecking.www.lazychecking.constant.Constant;

/**
 * Created by cwl on 2017/11/4.
 */

public class BaseFragment extends Fragment {

    protected Activity mContext;

    /**
     * 申请指定权限
     * @param code
     * @param permissions
     */
    public void requestPermission(int code,String...permissions){
        if (Build.VERSION.SDK_INT>=23){
            requestPermissions(permissions,code);
        }
    }
    public boolean hasPermission(String...permissions){
        for(String permission:permissions){
            //判断权限是否授予
            if(ContextCompat.checkSelfPermission(getActivity(),permission)!= PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return  true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case Constant.HARDWEAR_CAMERA_CODE:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    doOpenCamera();
                }
                break;
            case Constant.WRITE_READ_EXTERNAL_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doWriteSDCard();
                }
                break;
        }
    }

    private void doWriteSDCard() {
    }

    public void doOpenCamera() {

    }
}
