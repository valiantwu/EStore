package org.woo.devenv;

import org.woo.service.webserver.JettyServer;

public class WebAppService extends ServiceInit {
    public WebAppService() {
        super("web");
        System.setProperty("WEB_PORT", "11340");
        System.setProperty("dubbo.application.name", "web");
        System.setProperty("dubbo.consumer.url", "dubbo://127.0.0.1:20880");
    }

    @Override
    public void start() throws Exception {
        JettyServer.start();
    }

    public static void main(String[] args) {
        (new MServiceInit()).start();
    }
}
