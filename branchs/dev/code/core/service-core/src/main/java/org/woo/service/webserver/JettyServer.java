package org.woo.service.webserver;

import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import java.lang.management.ManagementFactory;
import java.net.BindException;

public class JettyServer {
    public static void start() throws Exception {
        String webport = getProperty("WEB_PORT", (String)null);
        if (webport == null) {
            String value = getProperty("JETTY_PORT", "11340");
            System.setProperty("WEB_PORT", value);
            webport = value;
        }

        int port = Integer.parseInt(webport);
        String contextPath = getProperty("JETTY_CONTEXT", "/EStore");
        if (!contextPath.startsWith("/")) {
            contextPath = "/" + contextPath;
        }

        String webAppPath = getProperty("JETTY_WEBAPP_PATH", "webapp");
        String maxThreads = getProperty("JETTY_MAXTHREADS", "200");
        QueuedThreadPool threadPool = new QueuedThreadPool(Integer.parseInt(maxThreads));
        threadPool.setName("http-request-pool");
        Server server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);
        MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
        server.addBean(mbContainer);
        WebAppContext context = new WebAppContext();
        context.setContextPath(contextPath);
        context.setDescriptor(webAppPath + "/WEB-INF/web.xml");
        context.setResourceBase(webAppPath + "/");
        context.setClassLoader(JettyServer.class.getClassLoader());
        context.setParentLoaderPriority(true);
        context.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
        server.setAttribute("org.eclipse.jetty.server.Request.maxFormContentSize", 2000000000);
        Servlet servlet = new Servlet();
        context.addServlet(new ServletHolder(servlet), "/tools/");
        context.addServlet(new ServletHolder(servlet), "/tools");
        ServletContextHandler monitorContext = new ServletContextHandler();
//        MonitorInstaller.install(monitorContext);
//        MonitorInstaller.install(context);
        ContextHandlerCollection handler = new ContextHandlerCollection();
        handler.addHandler(context);
        handler.addHandler(monitorContext);
        server.setHandler(handler);
        WebSocketServerContainerInitializer.configureContext(context);
        JmxHttpAdapter.start();
        try {
            server.start();
        } catch (BindException e) {
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        server.join();
    }

    private static String getProperty(String key, String defaultValue) {
        String value = System.getProperty(key);
        if (value != null) {
            return value;
        } else {
            value = System.getenv(key);
            return value != null ? value : defaultValue;
        }
    }
}
