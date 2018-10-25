package org.woo.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Router;

import java.util.List;

public class DebugRouter implements Router {
    private final URL url;

    public DebugRouter(URL url) {
        this.url = url;
    }

    @Override
    public URL getUrl() {
        return null;
    }

    @Override
    public <T> List<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        return null;
    }

    @Override
    public int compareTo(Router o) {
        return 0;
    }
}
