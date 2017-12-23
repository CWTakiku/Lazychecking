package com.lazychecking.www.lazychecking.network;

/**
 * Created by cwl on 2017/12/21.
 */

public class ServerInfo {
    private String ip;

    public long getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(long sensitivity) {
        this.sensitivity = sensitivity;
    }

    private long sensitivity;
    private static ServerInfo instance;
   private ServerInfo(){ sensitivity=-1; }
    public static synchronized ServerInfo getInstance(){
        if (instance==null)
            instance=new ServerInfo();
        return instance;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
