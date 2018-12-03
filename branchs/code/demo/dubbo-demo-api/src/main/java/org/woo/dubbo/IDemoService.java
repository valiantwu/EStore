package org.woo.dubbo;

public interface IDemoService {
    Object invoke(String serviceName, String methodName, Object... pars);
}
