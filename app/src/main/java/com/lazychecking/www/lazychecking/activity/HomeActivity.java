package com.lazychecking.www.lazychecking.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lazychecking.www.lazychecking.R;
import com.lazychecking.www.lazychecking.activity.base.BaseActivity;
import com.lazychecking.www.lazychecking.view.fragment.home.HomeFragment;
import com.lazychecking.www.lazychecking.view.fragment.home.MessageFragment;
import com.lazychecking.www.lazychecking.view.fragment.home.MineFragment;

/**
 * Created by cwl on 2017/11/4.
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener{

    private Fragment mCommonFragmentOne;
    private  Fragment mCurrent;//当前Fragment
    private FragmentManager fm;
    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;

    private RelativeLayout mHomeLayout;
    private RelativeLayout mPondLayout;
    private RelativeLayout mMessageLayout;
    private RelativeLayout mMineLayout;
    private TextView mHomeView;
    private TextView mPondView;
    private TextView mMessageView;
    private TextView mMineView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_layout);

        initView();
        mHomeFragment=new HomeFragment();
        fm=getFragmentManager();
        FragmentTransaction  fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout,mHomeFragment);
        fragmentTransaction.commit();
    }

    private void initView() {

        mHomeLayout= (RelativeLayout) findViewById(R.id.home_layout_view);
        mHomeLayout.setOnClickListener(this);
        mMessageLayout= (RelativeLayout) findViewById(R.id.message_layout_view);
        mMessageLayout.setOnClickListener(this);
        mMineLayout= (RelativeLayout) findViewById(R.id.mine_layout_view);
        mMineLayout.setOnClickListener(this);
        mPondLayout= (RelativeLayout) findViewById(R.id.pond_layout_view);
        mPondLayout.setOnClickListener(this);

        mHomeView = (TextView) findViewById(R.id.home_image_view);
        mPondView = (TextView) findViewById(R.id.fish_image_view);
        mMessageView = (TextView) findViewById(R.id.message_image_view);
        mMineView = (TextView) findViewById(R.id.mine_image_view);
        mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);

    }
   /*隐藏该Fragment*/
    private void hideFragment(Fragment mCommonFragment, FragmentTransaction fragmentTransaction){
        if (mCommonFragment!=null){
            fragmentTransaction.hide(mCommonFragment);
        }
    }
    @Override
    public void onClick(View v) {
        FragmentTransaction  fragmentTransaction=fm.beginTransaction();//开始事务
        switch (v.getId()){
            case R.id.home_layout_view:
            mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
            mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
            mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
            mMineView.setBackgroundResource(R.drawable.comui_tab_person);

            hideFragment(mCommonFragmentOne,fragmentTransaction);
            hideFragment(mMessageFragment,fragmentTransaction);
            hideFragment(mMineFragment,fragmentTransaction);
            if(mHomeFragment==null){
                mHomeFragment=new HomeFragment();
                fragmentTransaction.add(R.id.content_layout,mHomeFragment);
            }else{
                mCurrent=mHomeFragment;
                fragmentTransaction.show(mHomeFragment); //显示fragment
            }
            break;
            case R.id.message_layout_view:
                //changeStatusBarColor(R.color.color_e3e3e3);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                hideFragment(mCommonFragmentOne, fragmentTransaction);
                hideFragment(mHomeFragment, fragmentTransaction);
                hideFragment(mMineFragment, fragmentTransaction);
                if (mMessageFragment == null) {
                    mMessageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.content_layout, mMessageFragment);
                } else {
                    mCurrent = mMessageFragment;
                    fragmentTransaction.show(mMessageFragment);
                }
                break;
            case R.id.mine_layout_view:
                // changeStatusBarColor(R.color.white);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person_selected);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                hideFragment(mCommonFragmentOne, fragmentTransaction);
                hideFragment(mMessageFragment, fragmentTransaction);
                hideFragment(mHomeFragment, fragmentTransaction);
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.content_layout, mMineFragment);
                } else {
                    mCurrent = mMineFragment;
                    fragmentTransaction.show(mMineFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }
}
