package org.woo.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoService implements IDemoService {
    protected static final Logger logger = LoggerFactory.getLogger(DemoService.class);

    public DemoService() {
        logger.info("typeName"+DemoService.class.getTypeName());
    }

    @Override
    public Object invoke(String serviceName, String methodName, Object... pars) {
        return "erhewiryewirywieu";
    }
}
