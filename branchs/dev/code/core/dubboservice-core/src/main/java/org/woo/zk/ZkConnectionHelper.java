package org.woo.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZkConnectionHelper {
    public static final int DEFAULT_SESSION_TIMEOUT = 5000;

    public ZooKeeper connect(String hosts, int timeout) throws IOException {
        final CountDownLatch connectSingle = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper(hosts, timeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    ZkStatus.getInstance().setZkStatus(ZkStatus.Status.Connected);
                    connectSingle.countDown();
                }
                if (watchedEvent.getState() == Event.KeeperState.Disconnected) {
                    ZkStatus.getInstance().setZkStatus(ZkStatus.Status.DisConnected);
                }
            }
        });
        return zooKeeper;
    }
}
