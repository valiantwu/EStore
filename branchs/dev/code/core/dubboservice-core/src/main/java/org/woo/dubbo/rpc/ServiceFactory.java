package org.woo.dubbo.rpc;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private static Map<String, String> serviceMap = new HashMap<>();

    public ServiceFactory() {
    }

    public static <T> T getService(Class<T> clazz) {
        return (T) getService(clazz.getSimpleName());
    }

    private static Object getService(String serviceName) {
        String className= serviceMap.get(serviceName);
        if (className==null){
            throw new RuntimeException(String.format("%s对应服务没有找到",serviceName));
        }else {

        }
        return new Object();
    }
    static {
        serviceMap.put("metadataService","");
    }
}
