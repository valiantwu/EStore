package org.woo.devenv;

public abstract class ServiceInit {
    public ServiceInit(String appName) {
        System.setProperty("appName", appName);
        System.setProperty("clusterName", "dev-env");
        System.setProperty("dubbo.registry.protocol", "zookeeper");
        System.setProperty("dubbo.protocol.name", "dubbo");
        System.setProperty("configurl", "192.168.0.106:2181");
        System.setProperty("dubbo.registry.address", "zookeeper://192.168.0.106:2181");
//        System.setProperty("configurl", "172.20.114.73:2181");
    }

    abstract void start() throws Exception;
}
