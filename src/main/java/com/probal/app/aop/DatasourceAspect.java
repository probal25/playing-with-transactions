package com.probal.app.aop;

import com.mysql.cj.jdbc.ConnectionImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.sql.Connection;

@Aspect
@Component
public class DatasourceAspect {

    @Around("target(javax.sql.DataSource)")
    public Object logDatasourceConnectionInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Datasource connection info: " + proceedingJoinPoint.getSignature());
        Object returnValue = proceedingJoinPoint.proceed();
        if (returnValue instanceof Connection) {
            Connection proxyInstance = (Connection) Proxy.newProxyInstance(
                    ConnectionImpl.class.getClassLoader(),
                    new Class[]{Connection.class},
                    new ConnectionInvocationHandler((Connection) returnValue)
            );
            return proxyInstance;
        }
        return returnValue;
    }
}
