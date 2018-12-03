package org.woo.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.woo.redis.util.AopUtils;
import org.woo.redis.util.ExpressionUtils;

import java.lang.reflect.Method;

@Aspect
public class CacheAopAspect {
    private static final Logger logger = LoggerFactory.getLogger(CacheAopAspect.class);
    RedisAccess redisAccess;

    public RedisAccess getRedisAccessService() {
        return redisAccess;
    }

    public void setRedisAccessService(RedisAccess redisAccess) {
        this.redisAccess = redisAccess;
    }

    public Object doCacheable(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        Method method = AopUtils.getMethod(pjp);

        RedisCache cacheable = method.getAnnotation(RedisCache.class);

        Boolean isCacheEnable = "enable".equals(SystemCacheProperties.getProperty("system.cache.enable"));

        if (cacheable != null && !isCacheEnable) {

        }

        //-----------------------------------------------------------------------
        // 如果拦截的方法中没有Cacheable注解
        // 或者system.cache.enable的开关没打开
        // 则直接执行方法并返回结果
        //-----------------------------------------------------------------------
        if (cacheable == null || !isCacheEnable) {
            try {
                result = pjp.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return result;
        }

        String key = cacheable.key();

        //----------------------------------------------------------
        // 用SpEL解释key值
        //----------------------------------------------------------
        String keyVal = ExpressionUtils.parseKey(key, method, pjp.getArgs());

        //----------------------------------------------------------
        // 如果cacheable的注解中category为空取 类名+方法名
        //----------------------------------------------------------
        keyVal = pjp.getTarget().getClass().getSimpleName() + "_"
                + method.getName() + "_" + keyVal;


        Class returnType = ((MethodSignature) pjp.getSignature()).getReturnType();


        //-----------------------------------------------------------------------
        // 从redis读取keyVal，并且转换成returnType的类型
        //-----------------------------------------------------------------------
        result = redisAccess.get(keyVal, returnType);

        if (result == null) {
            try {
                //-----------------------------------------------------------------------
                // 如果redis没有数据则执行拦截的方法体
                //-----------------------------------------------------------------------
                result = pjp.proceed();
                int expireSeconds = 0;

                //-----------------------------------------------------------------------
                // 如果Cacheable注解中的expire为默认(默认值为-1)
                // 并且systemCache.properties中的system.cache.expire.default.enable开关为true
                // 则取system.cache.expire.default.seconds的值为缓存的数据
                //-----------------------------------------------------------------------
                if (cacheable.expire() == -1 &&
                        "enable".equals(SystemCacheProperties.getProperty("system.cache.expire.default.enable"))) {
                    expireSeconds = new Integer(SystemCacheProperties.getProperty("system.cache.expire.default.seconds"));
                } else {
                    expireSeconds = getExpireSeconds(cacheable);
                }
                //-----------------------------------------------------------------------
                // 把拦截的方法体得到的数据设置进redis，过期时间为计算出来的expireSeconds
                //-----------------------------------------------------------------------
                redisAccess.set(keyVal, result, expireSeconds);
                logger.debug("已缓存缓存:key=" + keyVal);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return result;
        }
        logger.debug("========从缓存中读取");
        logger.debug("=======:key   = " + key);
        logger.debug("=======:keyVal= " + keyVal);
        logger.debug("=======:val   = " + result);
        return result;
    }

    /**
     * 计算根据Cacheable注解的expire和DateUnit计算要缓存的秒数
     *
     * @param cacheable
     * @return
     */
    public int getExpireSeconds(RedisCache cacheable) {
        int expire = cacheable.expire();
        DateUnit unit = cacheable.dateUnit();
        if (expire <= 0) {
            return 0;
        }
        if (unit == DateUnit.MINUTES) {
            return expire * 60;
        } else if (unit == DateUnit.HOURS) {
            return expire * 60 * 60;
        } else if (unit == DateUnit.DAYS) {
            return expire * 60 * 60 * 24;
        } else if (unit == DateUnit.MONTHS) {
            return expire * 60 * 60 * 24 * 30;
        } else if (unit == DateUnit.YEARS) {
            return expire * 60 * 60 * 24 * 365;
        }
        return expire;
    }
}
