package org.woo.devenv;

import org.woo.service.start.Starter;

public class MServiceInit extends ServiceInit {
    public MServiceInit() {
        super("mservice");
        System.setProperty("dubbo.application.name", "mservice");
    }

    @Override
    public void start() {
        Starter.startCommon();
    }

    public static void main(String[] args) {
        (new MServiceInit()).start();
    }
}
