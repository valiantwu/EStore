package org.woo.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.List;

public class AppMaster {
    private static String clusterNode = "/Locks";
    private static CuratorFramework client;
    private volatile List<String> serverList;

    public void connectZookeeper() throws Exception {
        updateServerList();
    }

    private void updateServerList() throws Exception {
        List<String> newServerList = new ArrayList<String>();

        // watcher注册后，只能监听事件一次，参数true表示继续使用默认watcher监听事件
        GetChildrenBuilder subList = client.getChildren();
        serverList = newServerList;
        System.out.println("server list updated: " + serverList);
    }

    public static void main(String[] args) throws Exception {
        AppMaster ac = new AppMaster();
        ac.connectZookeeper();
        Thread.sleep(Long.MAX_VALUE);
    }

    static {
        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().connectString("192.168.1.106:2181").retryPolicy(new RetryNTimes(2147, 1000)).connectionTimeoutMs(5000).sessionTimeoutMs(50000);
        client = builder.build();
        client.start();
        try {
            Stat exits = client.checkExists().forPath(clusterNode);
            if (exits == null) {
                client.create().withMode(CreateMode.EPHEMERAL).forPath(clusterNode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
