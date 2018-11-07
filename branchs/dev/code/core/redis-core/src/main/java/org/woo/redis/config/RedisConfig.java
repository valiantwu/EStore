package org.woo.redis.config;

import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;

public final class RedisConfig implements Serializable {
    private static String ADDR = "192.168.1.106";

    private static int PORT = 6379;

    private static String AUTH = "admin";

    private static int DEFAULT_MAX_ACTIVE = 1024;

    private static int DEFAULT_MAX_IDLE = 10;

    private static int DEFAULT_MIN_IDLE = 5;

    private static int DEFAULT_MAX_WAIT = 5000;

    private static boolean DEFAULT_TEST_ON_BORROW = false;
    private static boolean DEFAULT_TEST_ON_RETURN = false;
    public JedisPoolConfig getPoolConfig(){
        JedisPoolConfig jedisPoolConfig= new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(DEFAULT_MAX_ACTIVE);
        jedisPoolConfig.setMaxIdle(DEFAULT_MAX_IDLE);
        jedisPoolConfig.setMinIdle(DEFAULT_MIN_IDLE);
        jedisPoolConfig.setMaxWaitMillis(DEFAULT_MAX_WAIT);
        jedisPoolConfig.setTestOnBorrow(DEFAULT_TEST_ON_BORROW);
        jedisPoolConfig.setTestOnReturn(DEFAULT_TEST_ON_RETURN);
        return jedisPoolConfig;
    }
}