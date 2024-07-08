package com.kli.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeAspect {
    @Around("execution(* com.kli.service.*.*(..))")
    public Object reportTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object proceed = proceedingJoinPoint.proceed();

        long enddTime = System.currentTimeMillis();

        System.out.println(proceedingJoinPoint.getSignature() + "总共耗时：" + (enddTime - startTime));

        return proceed;
    }
}
