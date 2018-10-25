package org.woo.redis.config;

import java.io.Serializable;

public final class RedisConfig implements Serializable {
    private static String ADDR = "192.168.1.106";

    private static int PORT = 6379;

    private static String AUTH = "admin";

    private static int MAX_ACTIVE = 1024;

    private static int MAX_IDLE = 200;

    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    private static boolean TEST_ON_BORROW = true;
}