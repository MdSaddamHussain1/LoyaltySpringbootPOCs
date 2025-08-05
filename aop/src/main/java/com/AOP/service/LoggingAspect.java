package com.AOP.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.AOP.service.*.*(..))")
    public void logBefore() {
        System.out.println("Executing method...");
    }

    @After("execution(* com.AOP.service.*.*(..))")
    public void logAfter() {
        System.out.println("Method execution finished.");
    }

    @AfterReturning(pointcut = "execution(* com.AOP.service.*.*(..))", returning = "result")
    public void logAfterReturning(Object result) {
        System.out.println("Returned: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.AOP.service.*.*(..))", throwing = "ex")
    public void logAfterThrowing(Exception ex) {
        System.out.println("Exception: " + ex.getMessage());
    }@Around("execution(* com.AOP.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println(" @Around - Before method: " + methodName);
        Object result = joinPoint.proceed(); // method execution
        System.out.println(" @Around - After method: " + methodName);
        return result;
    }
}

