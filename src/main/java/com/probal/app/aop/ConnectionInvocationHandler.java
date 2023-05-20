package com.probal.app.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

public class ConnectionInvocationHandler implements InvocationHandler {

    private Connection connection; // target object for connection

    public ConnectionInvocationHandler(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("connection trace :" + method.getName());
        Object returnValue = method.invoke(connection, args);
        return returnValue;
    }
}
