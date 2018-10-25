package org.woo.service.lookup;

/**
 * @author Administrator
 * @date 2018 07
 * org.woo.service.lookup.ServiceLookUp
 */
public abstract class ServiceLookUp {
    private static ServiceLookUp impl;
    protected abstract <T> T lookupImpl(String appId);
    protected abstract <T> T lookupImpl(Class<T> className);
    protected abstract <T> T lookupImpl(Class<T> className,String appId);
    public static void setImpl(ServiceLookUp serviceLookUp){
        impl=serviceLookUp;
    }
    public static <T> T lookup(Class<T> clazz,String appId) throws Exception {
        if (impl==null){
            throw new Exception("5984765943");
        }else {
            T t=impl.lookupImpl(clazz);
            if (t==null){
                throw new Exception("");
            }else {
                return t;
            }
        }
    }
}
