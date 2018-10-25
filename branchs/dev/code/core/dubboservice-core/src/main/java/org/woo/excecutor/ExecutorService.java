package org.woo.excecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 * @date 2018 07
 * org.woo.excecutor.ExecutorService
 */
public class ExecutorService extends AbstractService {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    public ExecutorService() {
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
        return null;
    }
}
