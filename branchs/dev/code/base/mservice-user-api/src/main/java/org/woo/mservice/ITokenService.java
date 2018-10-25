package org.woo.mservice;


/**
 * Created by Administrator on 2017/6/21.
 */
public interface ITokenService {
    Object getTokenByMaterId(String masterId);
    boolean updateToken(String key, String accessToken);
    boolean removeToken(String masterId);
}
