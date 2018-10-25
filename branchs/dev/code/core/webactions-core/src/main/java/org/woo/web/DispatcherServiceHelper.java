package org.woo.web;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DispatcherServiceHelper {
    private static Map<String,Object> proxyMap=new ConcurrentHashMap<>();
}
