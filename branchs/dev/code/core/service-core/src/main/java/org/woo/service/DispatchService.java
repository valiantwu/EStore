package org.woo.service;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author Administrator
 * @date 2018 08
 * org.woo.service.DispatchService
 */
@Service
public class DispatchService implements IDispatchService {
    @Override
    public Object invoke(String serviceName, String methodName, Object... pars) {
        return null;
    }
}
