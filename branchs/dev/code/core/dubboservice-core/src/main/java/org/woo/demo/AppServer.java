package org.woo.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppServer extends Thread {
    protected static final Logger logger = LoggerFactory.getLogger(AppServer.class);
    private static CuratorFramework client;
    private static String clusterNode = "/Locks";
    private static String serverNode = "/mylock";
    private String serverName;
    private long sleepTime;

    public void run() {
        try {
            connectZookeeper(serverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connectZookeeper(String address) throws Exception {

        // 关键方法，创建包含自增长id名称的目录，这个方法支持了分布式锁的实现
        // 四个参数：
        // 1、目录名称 2、目录文本信息
        // 3、文件夹权限，Ids.OPEN_ACL_UNSAFE表示所有权限
        // 4、目录类型，CreateMode.EPHEMERAL_SEQUENTIAL表示创建临时目录，session断开连接则目录自动删除
        client.getChildren().forPath(clusterNode);
        String createdPath = client.create().withMode(CreateMode.EPHEMERAL).forPath(address);
        logger.info("create: " + createdPath);
        Thread.sleep(sleepTime);
    }

    public AppServer(String serverName, long sleepTime) {
        this.serverName = serverName;
        this.sleepTime = sleepTime;
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
