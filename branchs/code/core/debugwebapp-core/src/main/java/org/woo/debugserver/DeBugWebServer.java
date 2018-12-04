package org.woo.debugserver;

import org.woo.devenv.WebAppService;

public class DeBugWebServer {
    public static void main(String[] args) throws Exception {
        WebAppService webService = new WebAppService();
        webService.start();
    }
}

