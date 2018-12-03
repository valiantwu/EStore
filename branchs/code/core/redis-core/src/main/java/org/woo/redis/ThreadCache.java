package org.woo.redis;

import java.io.Closeable;
import java.io.IOException;

public class ThreadCache implements Closeable {
    public static ThreadLocal<ThreadCache> threadCacheThreadLocal = ThreadLocal.withInitial(null);

    @Override
    public void close() throws IOException {

    }
}
