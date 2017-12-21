package com.lazychecking.www.lazychecking.view.fragment.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.lazychecking.www.lazychecking.R;
import com.lazychecking.www.lazychecking.network.ServerIP;

import static android.content.Context.MODE_PRIVATE;

public class PasswordDialogFragment extends DialogFragment {

    private View mContentView;
    private EditText mIpText;
    private Activity act;
    private SharedPreferences preference;
    private SharedPreferences.Editor editor;
    private ServerIP mServerIP;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
       mContentView= inflater.inflate(R.layout.dialog_ip, null);
        initView();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(mContentView)
                // Add action buttons
                .setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int id)
                            {
                                String ip=mIpText.getText().toString();
                                mServerIP=ServerIP.getInstance();
                                mServerIP.setIp(ip);
                                editor.putString("serverIP",ip);
                                editor.commit();
                            }
                        }).setNegativeButton("Cancel", null);
        return builder.create();
    }

    /* public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 去掉留白的标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContentView = inflater.inflate(R.layout.dialog_ip, container);
       initView();
        return mContentView;
    }*/

    private void initView() {

        mIpText= (EditText) mContentView.findViewById(R.id.id_password);
        act = getActivity();
        //ft=act.getFragmentManager().beginTransaction();
        preference=act.getSharedPreferences("crazyit", MODE_PRIVATE);
        editor=preference.edit();
        String ip=preference.getString("serverIP",null);
        if(ip!=null){
            mIpText.setText(ip);
        }

    }


}