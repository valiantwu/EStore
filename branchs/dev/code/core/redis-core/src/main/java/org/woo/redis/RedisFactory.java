package org.woo.redis;

import redis.clients.util.Pool;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @date 2018 11
 * org.woo.redis.RedisFactory
 */
public class RedisFactory {
    private static ConcurrentHashMap<String,Pool> poolMap=new ConcurrentHashMap<>();
}
