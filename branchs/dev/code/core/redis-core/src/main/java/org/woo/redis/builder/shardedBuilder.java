package org.woo.redis.builder;

import org.woo.redis.PoolBuilder;
import org.woo.redis.config.RedisConfig;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2018 11
 * org.woo.redis.builder.shardedBuilder
 */
public class shardedBuilder implements PoolBuilder<ShardedJedis> {
    @Override
    public Pool<ShardedJedis> build(String url) throws Exception {
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("192.168.1.106", 6379, "master"));
        JedisPoolConfig jedisPoolConfig = new RedisConfig().getPoolConfig();
        Pool<ShardedJedis> pool = new ShardedJedisPool(jedisPoolConfig, shards);
        return pool;
    }
}
