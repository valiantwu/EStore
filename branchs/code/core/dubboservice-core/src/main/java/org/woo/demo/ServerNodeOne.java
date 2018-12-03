package org.woo.demo;

public class ServerNodeOne {
    public static void main(String[] args) throws Exception {
        AppServer server1 = new AppServer("/Server1", 5000);
        server1.start();
    }
}
