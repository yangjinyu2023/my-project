package com.example.demo.dynamicDatasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(10)
@Aspect
@Component
public class DynamicDataSourceAop {
    @Pointcut("@annotation(com.example.demo.dynamicDatasource.Dynamic)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        // 获取 Dynamic 注解
        Dynamic dynamic = method.getAnnotation(Dynamic.class);
        if (dynamic == null) {
            Class < ? > targetClass = point.getTarget().getClass();
            dynamic = targetClass.getAnnotation(Dynamic.class);
            if (dynamic == null) {
                for (Class < ? > targetInterface : targetClass.getInterfaces()) {
                    dynamic = targetInterface.getAnnotation(Dynamic.class);
                }
            }
        }
        if (dynamic == null) {
            // 如果没有 Dynamic 注解，则设置默认数据源
            DynamicDataSourceHolder.setDataSourceKey(DynamicDataSourceHolder.MASTER);
        }
        else {
            // 否则按照指定的数据源切换
            DynamicDataSourceHolder.setDataSourceKey(dynamic.value());
        }
    }

    // 方法执行结束，清除当前线程的数据源信息
    @After("pointCut()")
    public void after() {
        DynamicDataSourceHolder.removeDataSource();
    }
}