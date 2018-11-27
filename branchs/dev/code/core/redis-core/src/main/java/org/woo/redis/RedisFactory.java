package org.woo.redis;

import org.woo.redis.builder.JedisBuilder;
import redis.clients.util.Pool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @date 2018 11
 * org.woo.redis.RedisFactory
 */
public class RedisFactory {
    private static ConcurrentHashMap<String, Pool<?>> poolMap = new ConcurrentHashMap<>();

    private static Pool<?> getPool(String url, boolean rebuild) {
        Pool<?> pool = null;
        if (rebuild) {
            synchronized (RedisFactory.class) {
                poolMap.remove(url);
            }
        } else {
            pool = poolMap.get(url);
        }
        if (pool == null) {
            synchronized (RedisFactory.class) {
                pool = poolMap.get(url);
                if (pool == null) {
                    try {
                        pool = new JedisBuilder().build(url);
                        poolMap.put(url, pool);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return pool;
    }

    private static Map<String, String> redisUrlMap = new ConcurrentHashMap<>();

    public static JedisClient getJedisClient(String url, boolean original) {
        url = redisUrlMap.get(url);
        JedisClient jedisClient = (JedisClient) getPool(url, false).getResource();
        return jedisClient;
    }
}
