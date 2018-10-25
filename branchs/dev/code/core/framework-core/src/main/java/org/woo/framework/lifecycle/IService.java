package org.woo.framework.lifecycle;

public interface IService {
    void startUp();
    void shutDwn();
    String getName();
    boolean isStarted();
    String sayHello(String name);
}
