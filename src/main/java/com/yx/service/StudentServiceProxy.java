package com.yx.service;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author LiuG
 * @DESCRIPTION
 * @create 2020/7/29
 */
public class StudentServiceProxy implements InvocationHandler {
    public Object target;

    public StudentServiceProxy(Object target) {
        this.target=target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(target, args);
        System.out.println("代理执行");
        return invoke;
    }
}
