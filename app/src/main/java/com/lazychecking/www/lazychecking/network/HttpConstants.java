package com.lazychecking.www.lazychecking.network;

/**
 * @function: 所有请求相关地址
 */
public class HttpConstants {
    /**
     * 方便我们更改地址
     */
    private static final String ROOT_URL = "http://lazy/";

    /**
     * 试卷上传接口
     */
    public static String UPLOAD= ROOT_URL + "paper.json";
    /**
     * 登陆接口
     */
    public static String LOGIN = ROOT_URL + "user_info.json";

    /**
     * 检查更新接口
     */
    public static String CHECK_UPDATE = ROOT_URL + "update.json";

    /**
     * 首页产品请求接口
     */
    public static String HOME_RECOMMAND = ROOT_URL + "home_data.json";
    /**
     * 课程详情接口
     */
    public static String COURSE_DETAIL = ROOT_URL + "course_detail.json";
    /**
     * apk更新下载地址
     */
    public static String UPDATE_APK = "http://www.imooc.com/mobile/mukewang.apk";

}


