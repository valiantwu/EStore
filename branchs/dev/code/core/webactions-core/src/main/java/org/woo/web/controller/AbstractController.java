package org.woo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.woo.dataentity.model.Page;
import org.woo.web.utils.JwtUtil;
import org.woo.web.utils.NetworkUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/25.
 */
public abstract class AbstractController<T> implements IController<T> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected JwtUtil jwtUtil = new JwtUtil();
    /*
     *返回值-code
     */
    public static final int RESCODE_SUCCESS = 200;				//成功
    public static final int RESCODE_SUCCESS_MSG = 201;			//成功(有返回信息)
    public static final int RESCODE_FAILD = 203;				//错误
    public static final int RESCODE_FAILD_MSG = 204;			//错误(有返回信息)
    public static final int RESCODE_EXCEPTION = 205;			//请求抛出异常
    public static final int RESCODE_NOLOGIN = 206;				//未登陆状态
    public static final int RESCODE_NOEXIST = 207;				//查询结果为空
    public static final int RESCODE_NOAUTH = 208;				//无操作权限
    public static final int RESCODE_TOKEN_FAILD = 209;				//错误

    /*
     *返回值-msg
     */
    public static final String AUTH_SUCCESS_MSG="token验证成功";
    public static final String AUTH_FAILD_EXP="TOKEN过期";
    public static final String AUTH_FAILD_IP="登录环境不安全";
    public static final String LOGIN_OTHERDEC="其他设备登录";
    public static final String LOGIN_USEERNAMENOTEXIT="用户名不存在";
    public static final String LOGIN_FAILD="用户名或者密码错误";
    public static final String LOGIN_SUCCESS="登录成功";
    public static final String RESDATA_SUCCESSED="数据请求成功";
    public static final String RESDATA_SIZEAERO="没有数据";
    public static final String RESDATA_REQERR="请求参数错误";
    /*
     *返回-key
     */
    public static final String KEY_DATA="data";
    public static final String KEY_CODE="code";
    public static final String KEY_MSG="msg";
    public static final String KEY_ERR="ERROR";
    public static final String KEY_AUTH="Authorization";
    public static final String KEY_EXCEPTION="exception";
    private HttpServletRequest request = null;
    private HttpServletResponse response = null;
    public String getIpV4(){
        String ip=null;
        request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            ip= NetworkUtil.getIpAddress(request);
            response.setContentType("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ip;
    }

    @Override
    public Map<String,Object> resultPageUtil(Page<T> page) {
        Map<String,Object> reMap=new HashMap<String,Object>();
        if(page!=null&&page.getCollection().size()>0){
            reMap.put(KEY_DATA,page.getCollection());
            reMap.put(KEY_CODE,RESCODE_SUCCESS);
            reMap.put(KEY_CODE,RESCODE_SUCCESS);
            return reMap;
        }else {
            reMap.put(KEY_CODE,RESCODE_NOEXIST);
            reMap.put(KEY_MSG,RESDATA_SIZEAERO);
            reMap.put(KEY_ERR,RESDATA_REQERR);
            return reMap;
        }
    }
    @Override
    public Map<String,Object> resultCollectionUtil(Collection<T> eCollection){
        Map<String,Object> reMap =new HashMap<String,Object>();
        if (eCollection!=null&&eCollection.size()>0){
            reMap.put(KEY_DATA,eCollection);
            reMap.put(KEY_CODE,RESCODE_SUCCESS);
            reMap.put(KEY_MSG,RESDATA_SUCCESSED);
            return reMap;
        }
        else
        {
            reMap.put(KEY_CODE,RESCODE_FAILD);
            reMap.put(KEY_MSG,RESDATA_SIZEAERO);
            return reMap;
        }
    }
    @Override
    public Map<String,Object> resultEUtil(Object e){
        Map<String,Object> reMap =new HashMap<String,Object>();
        if (e!=null&&!"".equals(e)){
            reMap.put(KEY_DATA,e);
            reMap.put(KEY_CODE,RESCODE_SUCCESS);
            reMap.put(KEY_MSG,RESDATA_SUCCESSED);
            return reMap;
        }
        else
        {
            reMap.put(KEY_CODE,RESCODE_FAILD);
            reMap.put(KEY_MSG,RESDATA_SIZEAERO);
            return reMap;
        }
    }
}
