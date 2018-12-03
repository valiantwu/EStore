package org.woo.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) throws Exception {
        @SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/demo-consumer.xml"});
        context.start();
        // obtain proxy object for rdoemote invocation
        IDemoService demoService = (IDemoService) context.getBean("demoService");
        // execute remote invocation
        Object hello = demoService.invoke("world","4ewerw34","ewrew");
        // show the result
        System.out.println(hello);

    }
}
