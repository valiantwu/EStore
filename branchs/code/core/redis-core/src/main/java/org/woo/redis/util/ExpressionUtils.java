package org.woo.redis.util;

import java.lang.reflect.Method;

public class ExpressionUtils {
    /**
     * 获取缓存的key
     * key 定义在注解上，支持SPEL表达式
     * 注： method的参数支持Javabean和Map
     * method的基本类型要定义为对象，否则没法读取到名称
     * <p>
     * example1:
     * Phone phone = new Phone();
     * "#{phone.cpu}"  为对象的取值
     * example2:
     * Map apple = new HashMap(); apple.put("name","good apple");
     * "#{apple[name]}"  为map的取值
     * example3:
     * "#{phone.cpu}_#{apple[name]}"
     *
     * @param key
     * @param method
     * @param args
     * @return
     */
    public static String parseKey(String key, Method method, Object[] args) {
        Object returnVal = null;
        return returnVal == null ? null : returnVal.toString();
    }
}
