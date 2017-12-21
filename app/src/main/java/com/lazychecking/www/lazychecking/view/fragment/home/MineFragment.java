package com.lazychecking.www.lazychecking.view.fragment.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lazychecking.www.lazychecking.R;
import com.lazychecking.www.lazychecking.network.ServerIP;
import com.lazychecking.www.lazychecking.view.fragment.BaseFragment;
import com.lazychecking.www.lazychecking.view.fragment.dialog.AboutInfo;
import com.lazychecking.www.lazychecking.view.fragment.dialog.PasswordDialogFragment;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by cwl on 2017/11/4.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener{

    /**
     *
     *UI
     */
    private View mContentView;
    private TextView mIPSet;
    private TextView mAbout;
    private ServerIP mIP;
    private SharedPreferences preference;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mContentView=inflater.inflate(R.layout.fragment_mine_layout,null,false);
        initView();
        return mContentView;
    }

    private void initView() {
        mIPSet= (TextView) mContentView.findViewById(R.id.video_setting_view);
        mIPSet.setOnClickListener(this);
        mAbout= (TextView) mContentView.findViewById(R.id.my_qrcode_view);
        mAbout.setOnClickListener(this);
        preference=mContext.getSharedPreferences("crazyit", MODE_PRIVATE);
        editor=preference.edit();
        mIP=ServerIP.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.video_setting_view:
                PasswordDialogFragment passwordDialogFragment = new PasswordDialogFragment();
                passwordDialogFragment.show(getFragmentManager(), "PasswordDialogFragment");

                break;
            case R.id.my_qrcode_view:
                AboutInfo aboutinto=new AboutInfo();
                aboutinto.show(getFragmentManager(),"AboutInfo");
                break;
        }
    }
}
