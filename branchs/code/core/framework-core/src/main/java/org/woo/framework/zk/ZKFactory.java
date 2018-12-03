package org.woo.framework.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ConcurrentHashMap;

public class ZKFactory {
    public static ConcurrentHashMap<String, CuratorFramework> pollMap = new ConcurrentHashMap<String, CuratorFramework>();

    public static CuratorFramework getZkClient(String url) {
        if (pollMap.containsKey(url)) {
            return pollMap.get(url);
        } else {
            Class<ZKFactory> clazz = ZKFactory.class;
            synchronized (clazz) {
                if (pollMap.containsKey(url)) {
                    return pollMap.get(url);
                } else {
                    CuratorFramework curatorFramework = null;
                    curatorFramework = CuratorFrameworkFactory.newClient(url, new ExponentialBackoffRetry(100, 3));
                    curatorFramework.start();
                    pollMap.put(url, curatorFramework);
                    return curatorFramework;
                }
            }
        }
    }
}
