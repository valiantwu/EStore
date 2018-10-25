package org.woo.framework.thread;

import org.woo.framework.lifecycle.LifeCycleServer;

import java.util.concurrent.ConcurrentHashMap;

public class ThreadLifeCycleManager {
    private static final int MAXSIZE = 100;
    private static ConcurrentHashMap<LifeCycleServer, ThreadLifeCycleManager.orderListener> listenerConcurrentHashMap = new ConcurrentHashMap<>();

    public static void start() {
    }

    public static void end() {
    }

    public static class orderListener implements Comparable<ThreadLifeCycleManager.orderListener> {
        private ThreadLifeCycleListener listener;
        private int order;

        public orderListener(int order, ThreadLifeCycleListener listener) {
            this.listener = listener;
            this.order = order;
        }

        @Override
        public int compareTo(orderListener o) {
            return 0;
        }
    }
}
