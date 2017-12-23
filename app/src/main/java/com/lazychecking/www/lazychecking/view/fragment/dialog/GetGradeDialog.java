package com.lazychecking.www.lazychecking.view.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lazychecking.www.lazychecking.R;
import com.lazychecking.www.lazychecking.model.result.GradeResult;
import com.lazychecking.www.lazychecking.network.ServerInfo;

import static com.lazychecking.www.lazychecking.constant.Constant.MSG_RESEND;

/**
 * Created by cwl on 2017/12/22.
 */

public class GetGradeDialog extends DialogFragment implements View.OnClickListener{
    private static int counter = 0;
    private View mContentView;
    private TextView mReusltText;
    private TextView msentivity;
    private GradeResult gradeResult;
    private Button maddBtn;
    private Button mredBtn;
    private ServerInfo serverinfo;
    private Handler mHandler;

    public void getHandler(Handler handler)
    {
        this.mHandler=handler;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mContentView= inflater.inflate(R.layout.dialog_getgrade, null);
        initView();
        builder.setView(mContentView)
                // Add action buttons
                .setPositiveButton("重新生成",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int id)
                            {
                           mHandler.sendEmptyMessage(MSG_RESEND);
                            }
                        }).setNegativeButton("确认", null);
        return builder.create();
    }

    private void initView() {
        mReusltText= (TextView) mContentView.findViewById(R.id.result_text);
        mReusltText.setTextColor(Color.RED);
        msentivity= (TextView) mContentView.findViewById(R.id.textView5);
        maddBtn= (Button) mContentView.findViewById(R.id.button3);
        maddBtn.setOnClickListener(this);
        mredBtn= (Button) mContentView.findViewById(R.id.button4);
        mredBtn.setOnClickListener(this);
        serverinfo=ServerInfo.getInstance();
        gradeResult=GradeResult.getInstance();
        if(gradeResult.getGradestring()!=null)
        utilstring(gradeResult.getGradestring());
        if(serverinfo.getSensitivity()!=-1)
        msentivity.setText("灵敏度:"+Long.toString(serverinfo.getSensitivity()));
        Bundle args = new Bundle();
    }

    private void utilstring(String gradestring) {
        StringBuilder temp = new StringBuilder();
         int num=countStr(gradestring,'@');

        int flag=0;
        if(num==5) {
            String numgrade ="";

            for (int i = 0; i < gradestring.length(); i++) {

                if (gradestring.charAt(i) != '@') {
                   numgrade+=gradestring.charAt(i);
                } else {
                   if(flag==0)
                   {
                       temp.append("未检测出答案的数量: ");

                   }
                   if(flag==1)
                   {
                       temp.append("\n听力分数: ");
                   }
                    if(flag==2)
                    {
                        temp.append("\n单项选择分数: ");
                    }
                    if(flag==3)
                    {
                        temp.append("\n完形填空分数:");
                    }
                    if(flag==4)
                    {
                        temp.append("\n该页面总分: ");
                    }
                    temp.append(numgrade+"\n");
                    numgrade="";
                    flag++;
                }
            }
        }
        else
        {
            flag=0;
            String numgrade ="";

            for (int i = 0; i < gradestring.length(); i++) {

                if (gradestring.charAt(i) != '@') {
                    numgrade+=gradestring.charAt(i);
                } else {
                    if(flag==0)
                    {
                        temp.append("未检测出答案的数量: ");

                    }
                    if(flag==1)
                    {
                        temp.append("\n阅读理解分数: ");
                    }
                    if(flag==2)
                    {
                        temp.append("\n补全对话分数: ");
                    }
                    if(flag==3)
                    {
                        temp.append("\n该页面总分: ");
                    }

                    temp.append(numgrade+"\n");
                    numgrade="";
                    flag++;
                }
            }
        }
        mReusltText.setText(temp.toString());
        //Log.i("info2", "utilstring: "+temp.toString());
    }
    public int countStr(String str1, char c) {
        int num=0;
       for(int i=0;i<str1.length();i++)
       {
           if(str1.charAt(i)==c){
               num++;
           }
       }
        return num;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button4:
                if(serverinfo.getSensitivity()!=-1)
                {
                    if (serverinfo.getSensitivity()>0) {
                        long temp = serverinfo.getSensitivity() - 1;
                        serverinfo.setSensitivity(temp);
                        msentivity.setText("灵敏度:"+Long.toString(temp));
                    }

                }
                break;
            case R.id.button3:
                if(serverinfo.getSensitivity()!=-1)
                {
                    if(serverinfo.getSensitivity()<19) {
                        long temp = serverinfo.getSensitivity() + 1;
                        serverinfo.setSensitivity(temp);
                        msentivity.setText("灵敏度:"+Long.toString(temp));
                    }

                }
                break;

        }
    }
}
