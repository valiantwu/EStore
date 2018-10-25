package org.woo.service.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.woo.framework.lifecycle.IService;

public class RedisAccessService implements IService {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public RedisAccessService() {
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
    public String sayHello(String s) {

        return this.getClass().getSimpleName();
    }
}
