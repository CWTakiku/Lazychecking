package com.lazychecking.www.lazychecking.network;

import android.util.Log;

import com.lazychecking.www.lazychecking.model.student.Student;
import com.lazychecking.www.lazychecking.model.user.User;
import com.lazychecking.www.lazychecking.okhttp.CommonOkHttpClient;
import com.lazychecking.www.lazychecking.okhttp.listener.DisposeDataHandle;
import com.lazychecking.www.lazychecking.okhttp.listener.DisposeDataListener;
import com.lazychecking.www.lazychecking.okhttp.request.CommonRequest;
import com.lazychecking.www.lazychecking.okhttp.request.RequestParams;

/**
 * Created by cwl on 2017/11/24.
 */

public class RequestCenter {

    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }
    /**
     * 请求登录
     * @param name
     * @param pwd
     * @param listener
     */
    public static void requestLogin(String name,String pwd,DisposeDataListener listener){
        RequestParams params=new RequestParams();
        params.put("mb",name);
        params.put("pwd",pwd);
        RequestCenter.postRequest(HttpConstants.LOGIN,params,listener,User.class);
    }
    public static void requestUpload(String url,RequestParams params, DisposeDataListener listener){
        CommonOkHttpClient.upLoadpic(CommonRequest.
                createMultiPostRequest(url,params),new DisposeDataHandle(listener, Student.class));
        Log.i("info1", "upload: ");
    }

}
