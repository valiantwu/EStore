package org.woo.service;

/**
 * @author Administrator
 * @date 2018 08
 * org.woo.service.IDispatchService
 */
public interface IDispatchService {
    Object invoke(String serviceName, String methodName, Object... pars);
}
