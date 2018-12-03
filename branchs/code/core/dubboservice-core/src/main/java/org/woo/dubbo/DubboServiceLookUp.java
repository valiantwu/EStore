package org.woo.dubbo;

import org.woo.service.lookup.ServiceLookUp;

/**
 * @author Administrator
 * @date 2018 07
 * org.woo.dubbo.DubboServiceLookUp
 */
public class DubboServiceLookUp extends ServiceLookUp {
    @Override
    protected <T> T lookupImpl(String s) {
        return null;
    }

    @Override
    protected <T> T lookupImpl(Class<T> aClass) {
        return null;
    }

    @Override
    protected <T> T lookupImpl(Class<T> aClass, String s) {
        return null;
    }
}
