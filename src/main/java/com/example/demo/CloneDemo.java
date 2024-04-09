package com.example.demo;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CloneDemo {
    public static void main(String[] args) {
        System.out.println(new BigDecimal("0.234").max(new BigDecimal("0.54")).toString());
        QueryCondition queryCondition = new QueryCondition();
        List<Long> person = new ArrayList<>();
        person.add(1L);
        person.add(2L);
        queryCondition.setPerson(person);
        QueryCondition queryCondition1 = queryCondition.clone();
        queryCondition1.getPerson().add(3L);
        queryCondition.getPerson().forEach(System.out::println);
        try {
            Class clazz = Class.forName("com.example.demo.QueryCondition");
            QueryCondition queryCondition2 = (QueryCondition) clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(Modifier.toString(field.getModifiers()));
            }
            Constructor[] constructors = clazz.getConstructors();
            for (Constructor constructor : constructors) {
                System.out.println(Modifier.toString(constructor.getModifiers()));
            }
            Constructor declaredConstructor = clazz.getDeclaredConstructor(new Class[]{List.class});
            System.out.println(declaredConstructor.getName());
            Method method = clazz.getDeclaredMethod("setPerson", List.class);
            System.out.println(Modifier.toString(method.getModifiers()));
            System.out.println(Modifier.isFinal(method.getModifiers()));
            System.out.println(Modifier.isStatic(method.getModifiers()));
            Class[] parameterTypes = method.getParameterTypes();
            for (Class parameterType : parameterTypes) {
                System.out.println(parameterType.getName());
            }
            try {
                method.invoke(queryCondition2, queryCondition.getPerson());
                queryCondition2.getPerson().forEach(System.out::println);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}