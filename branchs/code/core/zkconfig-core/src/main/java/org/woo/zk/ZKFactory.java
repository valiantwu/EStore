package org.woo.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.woo.exception.SystemException;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @date 2018 10
 * org.woo.zk.ZKFactory
 */
public class ZKFactory {
    private static ConcurrentHashMap<String,CuratorFramework> poolMap=new ConcurrentHashMap<>();
    public ZKFactory(){}
    public static CuratorFramework getZKClient(final String url){
        if (poolMap.contains(url)){
            return poolMap.get(url);
        }else {
            Class zk=ZKFactory.class;
            synchronized (ZKFactory.class){
                if (poolMap.contains(url)){
                    return poolMap.get(url);
                }else {
                    CuratorFramework curatorFramework = null;
                    try {
                        CuratorFramework client= CuratorFrameworkFactory.newClient(url,new ExponentialBackoffRetry(1000,3));
                        client.start();
                        poolMap.put(url,client);
                        curatorFramework=client;
                    }catch (Exception e){
                        throw new SystemException("新建节点失败");
                    }
                    return curatorFramework;
                }
            }
        }
    }
}
