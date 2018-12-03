package org.woo.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class BaseZookkeeper implements Watcher {
    private ZooKeeper zooKeeper;
    private static final int SESSION_TIME_OUT = 2000;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            System.out.println("watched removed event");
            countDownLatch.countDown();
        }
    }

    public void connectZookeeper(String host) throws Exception {
        zooKeeper = new ZooKeeper(host, SESSION_TIME_OUT, this);
        countDownLatch.await();
        System.out.println("conn succed");
    }

    public String createNode(final String path, final String data) throws Exception {
        return this.zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public List<String> getChildren(String path) throws Exception {
        return zooKeeper.getChildren(path, false);
    }

    public String getData(String path) throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData(path, false, null);
        if (data == null) {
            return "";
        }
        return new String(data);
    }

    public Stat setData(final String path, final String data) throws Exception {
        return this.zooKeeper.setData(path, data.getBytes(), -1);
    }

    public void deleteNode(final String path) throws KeeperException, InterruptedException {
        this.zooKeeper.delete(path, -1);
    }
}
