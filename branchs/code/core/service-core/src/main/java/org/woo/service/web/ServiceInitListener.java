package org.woo.service.web;

import org.woo.framework.lifecycle.LifeCycleServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServiceInitListener implements ServletContextListener {
    public ServiceInitListener() {

    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.setProperty("isWebNode", "true");
        LifeCycleServer.startup();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LifeCycleServer.shutDown();
    }
}
