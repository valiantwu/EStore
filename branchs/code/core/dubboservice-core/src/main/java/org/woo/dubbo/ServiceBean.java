package org.woo.dubbo;

import com.alibaba.dubbo.config.ServiceConfig;

/**
 * @author Administrator
 * @date 2018 08
 * org.woo.dubbo.ServiceBean
 */
public class ServiceBean extends ServiceConfig<Object>{
    private String[] appIds;

    public String[] getAppIds() {
        return appIds;
    }
}
