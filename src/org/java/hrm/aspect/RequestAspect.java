package org.java.hrm.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RequestAspect {

    /**
     * 定义切面
     * 所有由org.java.hrm.utils.ApiRequest注解注释的方法都将被拦截
     */
    //
    @Pointcut("@annotation(org.java.hrm.utils.ApiRequest)")
    public void ApiRequestAspect() {}

    /**
     * 定义环绕通知
     */
    @Around("ApiRequestAspect()")
    public Object processRequest(ProceedingJoinPoint jp) {
        Object result = null;
        try {
            Signature signature = jp.getSignature();
            result = jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("切面捕捉到了错误.....");
        } finally {
            return result;
        }
    }

}
