package org.woo.debugserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class SimplestServer {
    public static void main(String[] args) throws Exception{
        String path = System.getProperty("user.dir").replace("\\", "/");
        Server server = new Server(8080);
        WebAppContext context = new WebAppContext();
        context.setDescriptor(path+"/webapp/WEB-INF/web.xml");
        context.setResourceBase(path+"/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        server.start();

        server.join();
    }
}
