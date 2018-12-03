package org.woo.redis;

import redis.clients.jedis.BinaryJedisCommands;
import redis.clients.jedis.JedisCommands;

import java.io.Closeable;

public interface JedisClient extends JedisCommands,BinaryJedisCommands,Closeable{
}
