package org.woo.framework.constant;

/**
 * Created by Administrator on 2017/3/29.
 */
public class Constants {
    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";

    /**
     * jwt
     */
    public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";
    public static final int JWT_TTL = 60*60*1000;  //millisecond token有效时间
    public static final int JWT_REFRESH_INTERVAL = 2*60*1000;  //millisecond  token网络延迟时间
    public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond

    private static final String GIVENNAME="givenname";// 用户名
    private static final String EXPIRED="expired"; // 过期时间
    private static final String LASTOPERATE="lastoperate"; //
    private static final String ISS="iss";//
    private static final String IAT="iat";//
    private static final String AUD="aud";//
    private static final String SUB="sub";//
    private static final String EMAIL="email";//
    private static final String SURNAME="surname";//
    private static final String ROLELIST="rolelist";//


    /**
     * @fields AJAX_RESULT_SUCCESS : ajax返回成功
     */
    public static final String AJAX_RESULT_SUCCESS = "SUCCESS";
    /**
     * @fields ERROR_INFO_NAME : 错误信息名称
     */
    public static final String ERROR_INFO_NAME = "errorInfo";
    /**
     * @fields JSON_CONTENTTYPE : 返回json格式
     */
    public static final String JSON_CONTENTTYPE = "application/json;charset=UTF-8";
    /**
     * request异步请求类型
     */
    public static final String REQUEST_AJAX_TYPE = "XMLHttpRequest";
    /**
     * request异步请求头
     */
    public static final String REQUEST_AJAX_HEADR = "X-Requested-With";

    /**
     * @fields CONTENT_TYPE : 文档类型
     */
    public static final String CONTENT_TYPE ="text/html;charset=UTF-8";

    /**
     * @fields RESCODE_SUCCESS : 返回成功
     */
    public static final String RESCODE_SUCCESS = "SUCCESS";

    public  static  final int TOKEN_BYTE_LEN=32;
}
