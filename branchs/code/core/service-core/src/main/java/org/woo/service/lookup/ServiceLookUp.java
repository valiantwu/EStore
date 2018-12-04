package org.woo.service.lookup;

/**
 * @author Administrator
 * @date 2018 07
 * org.woo.service.lookup.ServiceLookUp
 */
public abstract class ServiceLookUp {
    private static ServiceLookUp impl;

    protected abstract <T> T lookupImpl(Class<T> className, String mod);

    public static void setImpl(ServiceLookUp serviceLookUp) {
        impl = serviceLookUp;
    }

    public static <T> T lookup(Class<T> clazz,String mod) throws Exception {
        if (impl == null) {
            throw new Exception("mservice is starting");
        } else {
            T t = impl.lookupImpl(clazz,mod);
            if (t == null) {
                throw new Exception("mservice not running");
            } else {
                return t;
            }
        }
    }
}
