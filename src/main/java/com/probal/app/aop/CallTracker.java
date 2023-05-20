package com.probal.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
// All non-functional code will be written here (like Logging, Transactions)
public class CallTracker {

    @Pointcut("within(com..service.*) || within(com..repository.*)")
    public void serviceAndRepoMethodPointCut() {

    }

    @Pointcut("within(com..service.*)")
    public void serviceMethodPointCut() {

    }

//    @Before("serviceAndRepoMethodPointCut()")
//    public void logBeforeMethodCall() {
//        System.out.println("Method is starting . . . ");
//    }
//
//    @After("serviceMethodPointCut()")
//    public void logAfterMethodCall() {
//        System.out.println("Method execution has completed . . . ");
//    }

    @Around("serviceMethodPointCut()")
    public Object logAroundMethodCall(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // before
        System.out.println("method call started: " + proceedingJoinPoint.getSignature().getName());

        // method invocation
        Object returnValue = proceedingJoinPoint.proceed();

        // after
        System.out.println("method call finished: " + proceedingJoinPoint.getSignature().getName());

        return returnValue;
    }

}
