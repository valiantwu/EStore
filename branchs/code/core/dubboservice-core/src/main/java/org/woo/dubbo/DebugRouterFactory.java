package org.woo.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.cluster.Router;
import com.alibaba.dubbo.rpc.cluster.RouterFactory;
@Activate
public class DebugRouterFactory implements RouterFactory{
    @Override
    public Router getRouter(URL url) {
        return new DebugRouter(url);
    }
}
