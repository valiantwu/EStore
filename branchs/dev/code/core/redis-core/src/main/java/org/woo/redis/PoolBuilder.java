package org.woo.redis;

import redis.clients.util.Pool;

public interface PoolBuilder<T> {
    public Pool<T> build(String url) throws Exception;
}
