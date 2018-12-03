package org.woo.demo;

public class ServerNodeTwo {
    public static void main(String[] args) throws Exception {
        AppServer server1 = new AppServer("/Server2", 10000);
        server1.start();
    }
}
