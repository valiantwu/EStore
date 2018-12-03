package org.woo.service.start;

import org.woo.framework.lifecycle.LifeCycleServer;

import java.util.concurrent.CountDownLatch;

public class Starter {
    public Starter() {
    }

    public static void startCommon() {
        Start();
    }

    public static void Start() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        LifeCycleServer.startup();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    LifeCycleServer.shutDown();
                } finally {
                    countDownLatch.countDown();
                }
            }
        });
        while (countDownLatch.getCount() > 0L) {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

