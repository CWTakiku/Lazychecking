package com.lazychecking.www.lazychecking.view.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.lazychecking.www.lazychecking.R;

/**
 * Created by cwl on 2017/12/21.
 */

public class AboutInfo extends DialogFragment {

    private View mContentView;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_aboutinfo, null);
        builder.setView(view)
                // Add action buttons
               .setNegativeButton("Cancel", null);
        return builder.create();
    }

    /*public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 去掉留白的标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContentView = inflater.inflate(R.layout.dialog_aboutinfo, container);
       initView();
        return mContentView;
    }*/





}
