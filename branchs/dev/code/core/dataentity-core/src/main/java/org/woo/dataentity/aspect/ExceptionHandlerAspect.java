package org.woo.dataentity.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/3/21.
 */
@Aspect
public class ExceptionHandlerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAspect.class);

    /**
     * 异常表达式：所有CloudFlowFormService类里面的方法
     */
    private final String EXPRESSION = "execution(* com.yunzhijia.linkerp.websocket.service.inner.impl.*.*(..))";

    /**
     * 异常处理切入点
     */
    @Pointcut(EXPRESSION)
    public void exceptionPoint() {
    }

    @Around("exceptionPoint()")
    public Object handleException(ProceedingJoinPoint pjp) {

        Object[] args = null;

        try {
            args = pjp.getArgs();
            checkArgument(args);
            return pjp.proceed(args);
        } catch (Throwable t) {
            String methodName = pjp.getSignature().getName();
            logger.error("[异常] 方法[{}]，参数:{}", methodName, Arrays.toString(args), t);
            return new String(t.getLocalizedMessage());
        }
    }

    /**
     * CloudflowFormService中的方法至少需要1个参数
     *
     * @param args
     */
    private void checkArgument(Object[] args) {

        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("没有参数");
        }
    }

}
