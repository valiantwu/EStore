package org.woo.dubbo;

public interface IService {
    void startUp();

    void shutDwn();

    String getName();

    boolean isStarted();

    String sayHello(String var1);
}
