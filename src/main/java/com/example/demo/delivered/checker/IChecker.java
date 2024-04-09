package com.example.demo.delivered.checker;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface IChecker < T > {
    int getCode();

    CheckResult check(T t, ICheckerChain chain);

    @SuppressWarnings("rawtypes")
    default boolean isGenericsAdaptive(Class targetClass) {
        Type[] genericInterfaces = this.getClass().getGenericInterfaces();
        if (genericInterfaces.getClass().isAssignableFrom(ParameterizedType[].class)) {
            for (Type genericInterface : genericInterfaces) {
                ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                return actualTypeArguments[0] == targetClass;
            }
        }
        return false;
    }
}