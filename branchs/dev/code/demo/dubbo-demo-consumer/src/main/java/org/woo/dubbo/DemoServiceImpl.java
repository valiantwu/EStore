package org.woo.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoServiceImpl implements IService {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public DemoServiceImpl() {
        log.info(this.getClass().getSimpleName());
    }

    @Override
    public void startUp() {

    }

    @Override
    public void shutDwn() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public String sayHello(String name) {
        log.info(this.getClass().getSimpleName() + name);
        return "Hello " + name;
    }
}
