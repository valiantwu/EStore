package org.woo.dubbo.service.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import org.woo.framework.lifecycle.LifeCycleServer;

@Activate(group = {Constants.PROVIDER}, order = -800)
public class ThreadLifeCycleFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = null;
        LifeCycleServer.startup();
        result = invoker.invoke(invocation);
        return result;
    }
}
