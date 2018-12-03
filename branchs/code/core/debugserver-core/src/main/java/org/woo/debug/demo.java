package org.woo.debug;

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
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }
    }
}
