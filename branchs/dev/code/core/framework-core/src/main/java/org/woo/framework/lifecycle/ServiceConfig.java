package org.woo.framework.lifecycle;

import java.util.ArrayList;

/**
 * @author Administrator
 * @date 2018 06
 * org.woo.framework.lifecycle.ServiceConfig
 */
public class ServiceConfig {
    private static String[] serviceClasses = null;
    private static IService[] services = null;
    private static String[] msServicesClasses = null;
    private static IService[] msServices = null;

    private static String[] webServicesClasses = null;
    private static IService[] webServices = null;

    public static IService[] getMsServices() {
        return msServices;
    }

    public static IService[] getServices() {
        return services;
    }

    public ServiceConfig() {
    }

    public static ArrayList<IService> getServiceList(String[] serviceClasses) {
        ArrayList<IService> iServices = new ArrayList<>();
        for (int i = 0; i < serviceClasses.length; i++) {
            iServices.add(createServiceByClass(serviceClasses[i]));
        }
        return iServices;
    }

    public static IService createServiceByClass(String className) {
        try {
            return (IService) Class.forName(className).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new Error("can't create service" + className);
        }
    }

    static {
        serviceClasses = new String[]{"org.woo.dubbo.DubboService"};
        msServicesClasses = new String[]{"org.woo.service.cache.RedisAccessService","org.woo.excecutor.ExecutorService"};
        ArrayList<IService> sList = getServiceList(serviceClasses);
        ArrayList<IService> msList = new ArrayList<>();
        ArrayList<IService> webList = new ArrayList<>();
        msList.addAll(sList);
        msList.addAll(getServiceList(msServicesClasses));
        webList.addAll(sList);
        msServices = msList.toArray(new IService[msList.size()]);
        services = msList.toArray(new IService[sList.size()]);
        webServices =webList.toArray(new IService[webList.size()]);
    }
}
