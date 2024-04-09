package com.example.demo;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * @author yangjinyu
 * @time 2021/5/6 15:45
 */
public class MyTest {

    public static void main(String[] args) {
        long num = 76798669327468L;
        char[] chars = new char[7];
        for (int i = chars.length - 1; i >= 0; --i) {
            chars[i] = (char) (num % 100);
            num = num / 100;
        }
        Stream.of(chars).forEach(System.out::print);
        System.out.println();
    }

    // print(chr(73)) # I
    //
    // print(chr(76)) # L
    //
    // print(chr(79)) # O
    //
    // print(chr(86)) # V
    //
    // print(chr(69)) # E
    //
    // print(chr(74)) # J
    //
    // print(chr(68)) #D

    // public static void main(String[] args) throws ClassNotFoundException {
    //
    // List<String> a= new ArrayList<>();
    // a.add("1");
    // a.add(null);
    // a.add("2");
    // System.out.println(a);
    // Integer b = -1670;
    // System.out.println(b.equals(null));
    //
    //
    //
    // Class clazz1 = QueryCondition.class;
    // Class clazz2 = Class.forName("com.example.demo.QueryCondition");
    // QueryCondition queryCondition = new QueryCondition();
    // Class clazz3 = queryCondition.getClass();
    // System.out.println(clazz1 == clazz2);
    // System.out.println(clazz2 == clazz3);
    //
    // Map<String, Object> m1 = new HashMap<>();
    // m1.put("A", 100);
    // m1.put("B", 200);
    // Map<String, Object> m2 = new HashMap<>();
    // m2.put("A", 50);
    // m2.put("B", 0);
    // Map<String, Object> m3 = new HashMap<>();
    // m3.put("A", 300);
    // m3.put("B", 400);
    // Map<String, Object> m4 = new HashMap<>();
    // m4.put("A", 0);
    // m4.put("B", -100);
    // List<Map<String,Object>> list = new ArrayList<>();
    // list.add(m1);
    // list.add(m2);
    // list.add(m3);
    // list.add(m4);
    // list = list.stream().sorted(
    // Comparator.comparingInt(o -> Integer.parseInt(o.get("A").toString())))
    // .collect(Collectors.toList());
    // System.out.println(list);
    // List<Object> list1 = list.stream().map(m->m.get("D")).collect(Collectors.toList());
    // System.out.println(list1);
    // }

    @Test
    public void test() {
        Integer a = Integer.valueOf("10");
        System.out.println(a.equals(1));
        System.out.println(a.equals(10));
        System.out.println(a.equals(null));
        int b = 10;
        Integer c = null;
        // System.out.println(b == c);
        String d = null;
        System.out.println(concat("22", d));
        System.out.println("11" + d);
        String aaa = String.format("难道%s中文", StringUtils.defaultString(null));
        System.out.println(aaa);
    }

    private static final ThreadLocal < StringBuilder > tempStr = ThreadLocal.withInitial(StringBuilder::new);

    public static String concat(String str1, String str2) {
        if (isEmpty(str1)) {
            return str2;
        }
        else if (isEmpty(str2)) {
            return str1;
        }
        else {
            StringBuilder sb = (StringBuilder) tempStr.get();

            String var3;
            try {
                var3 = sb.append(str1).append(str2).toString();
            }
            finally {
                sb.setLength(0);
            }

            return var3;
        }
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

}