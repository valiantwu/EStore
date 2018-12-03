package org.woo.redis.builder;

import org.woo.redis.PoolBuilder;
import org.woo.redis.config.RedisConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

/**
 * @author Administrator
 * @date 2018 11
 * org.woo.redis.builder.JedisBuilder
 */
public class JedisBuilder implements PoolBuilder<Jedis> {
    @Override
    public Pool<Jedis> build(String url) throws Exception {
        JedisPoolConfig jedisPoolConfig = new RedisConfig().getPoolConfig();
        Pool<Jedis> pool = new JedisPool(jedisPoolConfig, url, 6379, 5000);
        return pool;
    }
}
