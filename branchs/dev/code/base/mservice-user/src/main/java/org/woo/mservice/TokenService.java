package org.woo.mservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/6/21.
 */
public class TokenService implements ITokenService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object getTokenByMaterId(String masterId) {
        return "";
    }

    @Override
    public boolean updateToken(String key, String accessToken) {
        return false;
    }

    @Override
    public boolean removeToken(String masterId) {
        return false;
    }
}
