package org.woo.framework.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LifeCycleServer {
    private static final Logger logger = LoggerFactory.getLogger(LifeCycleServer.class);
    private static ArrayList<ServiceItem> startedServers = new ArrayList<>();
    private static List<ServiceItem> startingItem = Collections.synchronizedList(new LinkedList<>());
    private static AtomicInteger startThreadIndex = new AtomicInteger();

    public LifeCycleServer() {
    }

    public static void startup() {
        IService[] iServices = ServiceConfig.getMsServices();
        for (int i = 0; i < iServices.length; i++) {
            try {
                iServices[i].startUp();
            } catch (Throwable throwable) {
                logger.error("service" + iServices[i].getName() + "start error" + throwable);
            }
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                startService(new ServiceItem("RedisAccessService", "org.woo.service.cache.RedisAccessService"));
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public static void shutDown() {

    }

    private static void startService(final ServiceItem serviceItem) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                serviceItem.instance.startUp();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
