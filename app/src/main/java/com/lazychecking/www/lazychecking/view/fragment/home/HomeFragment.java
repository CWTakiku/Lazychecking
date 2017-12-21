package com.lazychecking.www.lazychecking.view.fragment.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.lazychecking.www.lazychecking.R;
import com.lazychecking.www.lazychecking.activity.CustomCamera;
import com.lazychecking.www.lazychecking.network.ServerIP;
import com.lazychecking.www.lazychecking.view.fragment.BaseFragment;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by cwl on 2017/11/4.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener{

    //定义
    private View mContentView;
    private Button mCameraBtn;
    private ServerIP mIP;
    private SharedPreferences preference;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mContext=getActivity();
        mContentView=inflater.inflate(R.layout.fragment_home_layout,container,false);
        initView();
        Log.i("info", "onClick1: ");
        return mContentView;
    }

    private void initView() {
        mCameraBtn= (Button) mContentView.findViewById(R.id.camera_btn);
        mCameraBtn.setOnClickListener(this);
        mIP=ServerIP.getInstance();
        preference=mContext.getSharedPreferences("crazyit", MODE_PRIVATE);
        editor=preference.edit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.camera_btn:
                //Log.i("info", "onClick: ");
                if(mIP.getIp()==null)
                {
                    //Log.i("info2", "onClick1: ");
                    String ip=preference.getString("serverIP",null);
                    if(ip==null) {
                        Toast.makeText(mContext, "你还没有设置服务器的IP地址", Toast.LENGTH_SHORT).show();
                        break;

                    }
                    mIP.setIp(ip);
                }

                startActivity(new Intent(mContext, CustomCamera.class));

                break;
        }

    }
}
