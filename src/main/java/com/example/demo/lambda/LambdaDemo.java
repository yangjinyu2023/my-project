package com.example.demo.lambda;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @description: java8 lambda 新特性演示
 * @author: yangjinyu
 * @time: 2020/4/13 20:05
 */
public class LambdaDemo {

    @Test
    public void test(){
        aaa("999", r-> System.out.println(r));
    }

    public void aaa(String value, Consumer < String > consumer) {
        if (StringUtils.isNotBlank(value)) {
            consumer.accept(value + "111");
        }
    }

    public static void main(String[] args) {

        // 接口实现
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                // 使用默认扩展方法
                return increment(a);
            }
        };
        System.out.println(formula.calculate(100));
        System.out.println(formula.increment(16));

        // lambda表达式
        List < String > names = Arrays.asList("tom", "mike", "jerry");
        Collections.sort(names, (a, b) -> a.compareTo(b));
        Collections.sort(names, String::compareTo);
        names.sort(String::compareTo);

        // 函数式接口
        GreetingService greetingService = message -> System.out.println("Hello " + message);
        greetingService.sayMessage("Tom");

        int num = 10;
        Conventer < String, Integer > conventer1 = from -> Integer.valueOf(from) + num;
        Conventer < String, Integer > conventer2 = Integer::valueOf;
        System.out.println(conventer1.convert("100"));
        System.out.println(conventer2.convert("100"));
        // 放开下面的赋值，会报错
        // num = 3;

        // Predicate
        Predicate < String > predicate = s -> s.length() > 0;
        System.out.println(predicate.test("111"));
        System.out.println(predicate.negate().test("111"));

        // Function
        Function < String, BigDecimal > toBigDecimal = BigDecimal::new;
        // 100
        System.out.println(toBigDecimal.apply("100"));
        // -100
        System.out.println(toBigDecimal.andThen(BigDecimal::negate).apply("100"));
        // 10010
        System.out.println(toBigDecimal.compose(x -> x + "10").apply("100"));

        Function < Integer, Integer > times2 = i -> i * 2;
        Function < Integer, Integer > squared = i -> i * i;
        // 先i*i然后i*2
        System.out.println(times2.compose(squared).apply(4));
        // 先i*2然后i*i
        System.out.println(times2.andThen(squared).apply(4));

        // Supplier
        Supplier < String > supplier = () -> {
            String m = "";
            m = m.concat("A");
            return m;
        };
        System.out.println(supplier.get());

        // Consumer
        Consumer < Integer > consumer = x -> {
            int a = x + 2;
            System.out.println(a);// 12
            System.out.println(a + "_");// 12_
        };
        consumer.andThen(x -> System.out.println("finish")).accept(10);

        // BiConsumer
        BiConsumer < String, Integer > biConsumer = (x, y) -> {
            System.out.println(x);
            System.out.println(y);
            System.out.println(x + y);
        };
        biConsumer.andThen((a, b) -> {
            System.out.println("finish " + a);
            System.out.println("finish " + b);
        }).accept("haha", 1);

        // Comparator
        Comparator < Integer > comparator = Integer::compareTo;
        System.out.println(comparator.compare(3, 3));
    }
}