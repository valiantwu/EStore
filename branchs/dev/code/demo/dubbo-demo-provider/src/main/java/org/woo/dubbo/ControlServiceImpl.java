package org.woo.dubbo;

import java.util.Map;

import org.springframework.stereotype.Service;
@Service
public class ControlServiceImpl implements IControlService{

    @Override
    public String sayHello(Map<String, Object> map) {
        System.out.println("You Have Arrived Producer");
        return "success";
    }
}
