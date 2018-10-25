package org.woo.dubbo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.compiler.support.ClassUtils;
import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.dubbo.config.*;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.woo.exception.SystemException;
import org.woo.service.DispatchService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @date 2018 06
 * org.woo.dubbo.DubboBeanConfig
 */
public class DubboBeanConfig {
    private static ConcurrentHashMap<String, Object> localServiceInstanceMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, ReferenceConfig> referenceConfigMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, ReferenceConfig> id2referenceConfigMap = new ConcurrentHashMap<>();
    protected static final Logger logger = LoggerFactory.getLogger(DubboBeanConfig.class);
    private static Set<ServiceConfig> serviceConfigSet = new ConcurrentHashSet<>();
    private static CuratorFramework client;
    private static String providerPackagePath;
    private static String dubboRootPath = "/dubbo";
    private static String identifiedPath = "/" + System.getProperty("clusterName") + UUID.randomUUID().toString();
    private static AtomicInteger reExportCount = new AtomicInteger(0);

    static {
        System.setProperty("dubbo.parameter.heartbeat", "3000");
        try {
            String url = System.getProperty("configurl");
            CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().connectString(url).retryPolicy(new RetryNTimes(3, 1000)).connectionTimeoutMs(5000).sessionTimeoutMs(60000);
            client = builder.build();
            client.start();
            Timer timer = new Timer("dubbomoniter");
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (dubboRootPath != null) {
                        try {
                            Stat exits = client.checkExists().forPath(dubboRootPath + identifiedPath);
                            if (exits == null) {
                                createIdentified();
                                logger.info(dubboRootPath + identifiedPath + " dubbo registry has losted");
                            }
                        } catch (Exception e) {
                            logger.error("dubbo.registry.address:" + System.getProperty("dubbo.registry.address"), e);
                            e.printStackTrace();
                        }

                    }
                }
            }, 10000L, 3000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DubboBeanConfig() {
    }

    public static void initRefServiceConfig(ReferenceConfig serviceConfig) {

    }

    public static void initServiceConfig(ServiceBean config) {
        String value = System.getProperty("dubbo.protocol.port", "28280");
        if (value != null) {
            System.setProperty("SERVICE_PORT", value);
        }
        String[] appIds = config.getAppIds();
        if (appIds == null) {
            config.export();
        } else {
            HashSet<String> idSet = new HashSet<>();
            for (String appid : appIds) {
                ServiceConfig service = new ServiceConfig();
                service.setApplication(config.getApplication());
                service.setProvider(config.getProvider());
                service.setRegistry(config.getRegistry());
                service.setProtocol(config.getProtocol());
                service.setInterface(config.getInterface());
                service.setRef(config.getRef());
                service.setTimeout(config.getTimeout());
                service.setGroup(appid);
                service.export();
                idSet.add(appid);
//                serviceConfigSet.add(service);
            }
        }
    }


    public static void start() throws ClassNotFoundException {
        ApplicationConfig applicationConfig = createApplicationConfig();
        RegistryConfig registryConfig = createRegistryConfig();
        applicationConfig.setRegistry(registryConfig);

        ProtocolConfig protocolConfig = createProtocolConfig();
        ProviderConfig providerConfig = createProviderConfig();

        providerConfig.setApplication(applicationConfig);
        providerConfig.setRegistry(registryConfig);
        providerConfig.setProtocol(protocolConfig);
        providerConfig.setExport(true);

        ConsumerConfig consumerConfig = createConsumerConfig();
        consumerConfig.setApplication(applicationConfig);
        consumerConfig.setRegistry(registryConfig);
        consumerConfig.setTimeout(1200000);
        String isWebService = System.getProperty("appName");
        String inter = "org.woo.service.IDispatchService";
        Class<?> model = Class.forName(inter);
        String clas = "org.woo.service.DispatchService";
        Object bean = (DispatchService) ClassUtils.newInstance(clas);
        if (isWebService != null && "mservice".equals(isWebService)) {
            ServiceConfig<DispatchService> serviceConfig = new ServiceConfig<DispatchService>();
//            serviceConfig.setId(inter);
            serviceConfig.setInterface(model);
            serviceConfig.setRef((DispatchService) bean);
            serviceConfig.setApplication(applicationConfig);
            serviceConfig.setRegistry(registryConfig);
            serviceConfig.setProvider(providerConfig);
            serviceConfig.setProtocol(protocolConfig);
            serviceConfig.setRegister(true);
            serviceConfig.setSent(true);
            serviceConfig.export();
        }
        ReferenceConfig<DispatchService> referenceConfig = new ReferenceConfig<DispatchService>();
        referenceConfig.setApplication(applicationConfig);
//            referenceConfig.setId(inter);
        referenceConfig.setInterface(model);
//            referenceConfig.setInterface(inter);
        referenceConfig.setCheck(true);
        referenceConfig.setProtocol(System.getProperty("dubbo.protocol.name", "dubbo"));
        referenceConfig.setUrl(System.getProperty("dubbo.consumer.url", "dubbo://127.0.0.1:20880"));
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setScope(Constants.CONSUMER);
        referenceConfig.setInit(true);
        referenceConfig.setConsumer(consumerConfig);
        referenceConfig.setRetries(2);
    }

    public static ProtocolConfig createProtocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
//        protocolConfig.setId(System.getProperty("dubbo.application.name"));
        protocolConfig.setName(System.getProperty("dubbo.protocol.name", "dubbo"));
        protocolConfig.setDispatcher("all");
        protocolConfig.setThreadpool("cached");
        protocolConfig.setThreads(30);
        protocolConfig.setRegister(true);
        protocolConfig.setHost("127.0.0.1");
        protocolConfig.setPort(20880);
        protocolConfig.setPayload(52428800);
        return protocolConfig;
    }

    private static ApplicationConfig createApplicationConfig() {
        String name = System.getProperty("dubbo.application.name", "EStore");
        ApplicationConfig applicationConfig = new ApplicationConfig(name);
        applicationConfig.setId(name);
        return applicationConfig;
    }

    public static ProviderConfig createProviderConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setDispatcher("all");
        providerConfig.setThreadpool("cached");
        providerConfig.setExport(true);
        providerConfig.setRetries(0);
        providerConfig.setThreads(30);
        providerConfig.setPayload(52428800);
        providerConfig.setLoadbalance("leastactive");
        return providerConfig;
    }

    public static ConsumerConfig createConsumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(Integer.parseInt(System.getProperty("dubbo.consumer.timeout", "5000")));
        consumerConfig.setRetries(0);
        consumerConfig.setLoadbalance("leastactive");
        return consumerConfig;
    }

    public static RegistryConfig createRegistryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        String url = System.getProperty("dubbo.registry.address");
        if (url == null) {
            url = System.getProperty("configurl");
        }
        if (url != null && url.trim().length() > 0) {
            registryConfig.setAddress(url);
            registryConfig.setProtocol(System.getProperty("dubbo.registry.protocol", "zookeeper"));
            registryConfig.setRegister(true);
            dubboRootPath = "/dubbo";
            createIdentified();
            return registryConfig;
        } else {
            throw new SystemException("dubbo address is not config");
        }
    }

    public static void createIdentified() {
        try {
            Stat exists = client.checkExists().forPath(dubboRootPath + identifiedPath);
            if (exists == null) {
                ((ACLBackgroundPathAndBytesable) client.create().withMode(CreateMode.EPHEMERAL)).forPath(dubboRootPath + identifiedPath);
            }
        } catch (Exception e) {
            logger.error("create dubbo error", e);
            e.printStackTrace();
        }
    }

    public static void ReExport() {
        serviceConfigSet.forEach((service) -> {
            service.unexport();
        });
        serviceConfigSet.clear();
        try {
            start();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static <T> T getLocalServiceInstance(Class<T> tClass) {
        return (T) localServiceInstanceMap.get(tClass.getName());
    }

    public static <T> T getLocalServiceInstance(String interfaze) {
        return (T) localServiceInstanceMap.get(interfaze);
    }

    public static void putLocalService(String interfaze, Object bean) {
        localServiceInstanceMap.put(interfaze, bean);
    }
}
