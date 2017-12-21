package com.lazychecking.www.lazychecking.network;

/**
 * Created by cwl on 2017/12/21.
 */

public class ServerIP {
    private String ip;
    private static ServerIP instance;
   private ServerIP(){}
    public static synchronized ServerIP getInstance(){
        if (instance==null)
            instance=new ServerIP();
        return instance;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
