package org.woo.dubbo.service.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * @author Administrator
 * @date 2018 08
 * org.woo.dubbo.service.filter.RequestContextFilter
 */
@Activate(group = {Constants.PROVIDER, Constants.CONSUMER}, order = 1000, before = "exception")
public class RequestContextFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return null;
    }
}
