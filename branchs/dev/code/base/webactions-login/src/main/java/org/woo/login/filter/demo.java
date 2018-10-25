package org.woo.login.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2018 06
 * org.woo.debug.demo
 */
public class demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
//        for (String item : list) {
//            if ("3".equals(item)) {
//                list.remove(item);
//            }
//        }
        for(int i=list.size()-1;i>=0;i--){
            if ("3".equals(list.get(i))){
                list.remove(i);
            }
        }
    }
}
