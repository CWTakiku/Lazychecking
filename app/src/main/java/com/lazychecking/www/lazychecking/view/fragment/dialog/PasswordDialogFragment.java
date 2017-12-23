package com.lazychecking.www.lazychecking.view.fragment.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.lazychecking.www.lazychecking.R;
import com.lazychecking.www.lazychecking.network.ServerInfo;

import static android.content.Context.MODE_PRIVATE;

public class PasswordDialogFragment extends DialogFragment {

    private View mContentView;
    private EditText mIpText;
    private EditText mSensitivity;
    private Activity act;
    private SharedPreferences preference;
    private SharedPreferences.Editor editor;
    private ServerInfo mServerIP;

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
                                String sen=mSensitivity.getText().toString();
                                Log.i("info2", "onClick: "+sen);
                                long sensitivity=Long.parseLong(sen);
                                mServerIP.setIp(ip);
                                mServerIP.setSensitivity(sensitivity);
                                editor.putString("serverIP",ip);
                                editor.putLong("sensitivity",sensitivity);
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
        mServerIP= ServerInfo.getInstance();
        mIpText= (EditText) mContentView.findViewById(R.id.id_password);
        mSensitivity= (EditText) mContentView.findViewById(R.id.id_sensitivity);
        act = getActivity();
        preference = act.getSharedPreferences("crazyit", MODE_PRIVATE);
        editor = preference.edit();

        //ft=act.getFragmentManager().beginTransaction();
        if(mServerIP.getIp()!=null)
            mIpText.setText(mServerIP.getIp());
        else{

            String ip = preference.getString("serverIP", null);

            if (ip != null) {
                mIpText.setText(ip);
               // Log.i("info2", "initView1: "+ip);
            }

        }
        if(mServerIP.getSensitivity()!=-1) {
            long sen=mServerIP.getSensitivity();
            mSensitivity.setText(Long.toString(sen));
        }
        else
        {
            long sen=preference.getLong("sensitivity",-1);
            if (sen != -1) {
                mSensitivity.setText(Long.toString(sen));
                //Log.i("info2", "initView1: "+sen);
            }
        }


    }


}