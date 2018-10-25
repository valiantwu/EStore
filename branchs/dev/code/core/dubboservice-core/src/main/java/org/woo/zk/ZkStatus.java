package org.woo.zk;

public class ZkStatus {
    private static ZkStatus instance = new ZkStatus();
    private static ZkStatus.Status zkStatus = null;

    public static ZkStatus getInstance() {
        return instance;
    }

    public ZkStatus.Status get() {
        return zkStatus;
    }

    public void setZkStatus(ZkStatus.Status zkStatus) {
        ZkStatus.zkStatus = zkStatus;
    }

    public static enum Status {
        Connected,
        DisConnected
    }
}
