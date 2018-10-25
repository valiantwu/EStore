package org.woo.dubbo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.woo.framework.lifecycle.IService;

public class DubboService implements IService {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    private boolean isStarted;

    public DubboService() {
        log.info(this.getClass().getSimpleName());
    }

    @Override
    public void startUp() {
        try {
            DubboBeanConfig.start();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            isStarted = true;
            log.info("DubboService started!");
        }
    }

    @Override
    public void shutDwn() {

    }

    @Override
    public String getName() {
        return "Dubbo";
    }

    @Override
    public boolean isStarted() {
        return this.isStarted;
    }

    @Override
    public String sayHello(String name) {
        log.info(this.getClass().getSimpleName() + name);
        return this.getClass().getSimpleName();
    }
}
