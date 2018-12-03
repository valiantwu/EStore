package org.woo.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
    public static void main(String[] args) throws Exception {
        @SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"spring/demo-provider.xml"});
        context.start();
        // press any key to exit
        System.in.read();
    }
}
