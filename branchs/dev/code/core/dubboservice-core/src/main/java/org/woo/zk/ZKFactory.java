package org.woo.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.woo.exception.SystemException;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @date 2018 08
 * org.woo.zk.ZKFactory
 */
public class ZKFactory {
    public static ConcurrentHashMap<String, CuratorFramework> poolMap = new ConcurrentHashMap();

    public ZKFactory() {
    }

    public static CuratorFramework getZKClient(String url){
        if (poolMap.containsKey(url)) {
            return (CuratorFramework)poolMap.get(url);
        } else {
            Class var1 = ZKFactory.class;
            synchronized(ZKFactory.class) {
                if (poolMap.containsKey(url)) {
                    return (CuratorFramework)poolMap.get(url);
                } else {
                    CuratorFramework curatorFramework;
                    try {
                        CuratorFramework client = CuratorFrameworkFactory.newClient(url, new ExponentialBackoffRetry(1000, 3));
                        client.start();
                        poolMap.put(url, client);
                        curatorFramework = client;
                    } catch (Exception e) {
                        throw new SystemException("");
                    }

                    return curatorFramework;
                }
            }
        }
    }
}
