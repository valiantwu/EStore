package org.woo.dubbo;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.ExporterListener;
import com.alibaba.dubbo.rpc.RpcException;
@Activate
public class DebugServiceExporterListener implements ExporterListener {
    @Override
    public void exported(Exporter<?> exporter) throws RpcException {

    }

    @Override
    public void unexported(Exporter<?> exporter) {

    }
}
