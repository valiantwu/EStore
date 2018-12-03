package org.woo.demo;

public class ZkStatus {
    private static ZkStatus instance = new ZkStatus();
    private static Status zkStatus = null;

    public static ZkStatus getInstance() {
        return instance;
    }

    public Status get() {
        return zkStatus;
    }

    public void setZkStatus(Status zkStatus) {
        ZkStatus.zkStatus = zkStatus;
    }

    public static enum Status {
        Connected,
        DisConnected
    }
}
