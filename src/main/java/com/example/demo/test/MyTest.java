package com.example.demo.test;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import com.example.demo.domain.Person;
import com.example.demo.utils.BeanUtil;
import com.example.demo.utils.DateUtil;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangjinyu
 * @time 2021/5/6 15:45
 */
@Slf4j
public class MyTest {
    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        List<Future> futureList = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            futureList.add(executor.submit(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (finalI == 2) {
                    throw new RuntimeException("111");
                }
                System.out.println("222");
            }));
        }
        try {
            for (Future future : futureList) {
                future.get();
            }
        } catch (Exception e) {
            log.error("",e);
            for (; ; ) {
                boolean isDone = false;
                for (Future future : futureList) {
                    isDone = future.isDone();
                }
                if (!isDone) {
                    Thread.sleep(1000);
                } else {
                    break;
                }
            }
            System.out.println("333");
        }finally {
            executor.shutdown();
        }


        System.out.println(DateUtil.daysBetween(20220516, 20220512));
        System.out.println(DateUtil.addDays("20220511", 30));
        System.out.println(new BigDecimal("23141.21").longValue());
        testStreamOperationIfDeepCloneOrNot();
        Class clazz1 = QueryCondition.class;
        Class clazz2 = Class.forName("com.example.demo.test.QueryCondition");
        QueryCondition queryCondition = new QueryCondition();
        Class clazz3 = queryCondition.getClass();
        System.out.println(clazz1 == clazz2);
        System.out.println(clazz2 == clazz3);

        Map<String, Object> m1 = new HashMap<>();
        m1.put("A", 100);
        m1.put("B", 200);
        Map<String, Object> m2 = new HashMap<>();
        m2.put("A", 50);
        m2.put("B", 0);
        Map<String, Object> m3 = new HashMap<>();
        m3.put("A", 300);
        m3.put("B", 400);
        Map<String, Object> m4 = new HashMap<>();
        m4.put("A", 0);
        m4.put("B", -100);
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        System.out.println(list.stream().filter(m -> m.get("A").toString().equals("100000"))
                .map(m -> m.get("B").toString()).collect(Collectors.toList()));
        list = list.stream().sorted(
                Comparator.comparingInt(o -> Integer.parseInt(o.get("A").toString())))
                .collect(Collectors.toList());
        System.out.println(list);
    }

    // stream 是否是深克隆？？ 结论：不是
    public static void testStreamOperationIfDeepCloneOrNot() {
        Map<String, Object> m1 = new HashMap<>();
        m1.put("A", 100);
        m1.put("B", 200);
        Map<String, Object> m2 = new HashMap<>();
        m2.put("A", 50);
        m2.put("B", 0);
        Map<String, Object> m3 = new HashMap<>();
        m3.put("A", 300);
        m3.put("B", 400);
        Map<String, Object> m4 = new HashMap<>();
        m4.put("A", 0);
        m4.put("B", -100);
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);

        System.out.println(list.toString());
        List<Map<String, Object>> anotherList = list.stream().filter(m -> "100".equals(m.get("A").toString())).collect(Collectors.toList());
        anotherList.forEach(m -> m.put("A", 10000000));
        System.out.println(anotherList.toString());
        System.out.println(list.toString());
    }

    private static void t1() {
        String collectSeq = "2022052517333172939127481";
        String feeBody = "2";
        System.out.println(collectSeq.substring(4, 5));
        System.out.println(collectSeq.substring(0, 4));
        System.out.println(collectSeq.substring(5));
        String fill = Integer.parseInt(collectSeq.substring(4, 5))
                + ("1".equals(feeBody) ? 2 : 3) + "";
        collectSeq = collectSeq.substring(0, 4).concat(fill).concat(collectSeq.substring(5));
        System.out.println(collectSeq);
    }
    private static void t2() {
        Person person = new Person();
        person.setId(111L);
        person.setName("tom");
        Map<String, Object> map = BeanUtil.beanToMap(person);
        System.out.println(map);
        person = BeanUtil.mapToBean(map, Person.class);
        System.out.println(person);
    }

    private final static int[]  mm={11,22};


    private static void t3() {
        String a = "11";
        System.out.println(a);
        change(a);
        System.out.println(a);
        Map<String, Object> map = new HashMap<>();
        map.put("11","11");
        System.out.println(map);
        changeRef(map);
        System.out.println(map);
        changeContext(map);
        System.out.println(map);
        mm[0] = 1;//不能改变final变量指向的引用，但是可以改变指向的对象的内容
        System.out.println(Arrays.toString(mm));
        int handledSize = 0, batchSize = 30;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        for (; ; ) {
            List<Integer> handleList = list.stream().skip(handledSize).limit(batchSize).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(handleList)) {
                System.out.println(handleList);
                handledSize += batchSize;
            } else {
                break;
            }
        }
    }

    private static void changeRef(Map<String, Object> map) {
        map = new HashMap<>();
        map.put("11", "22");
    }

    private static void changeContext(Map<String, Object> map) {
        map.put("11","33");
    }

    private static void change(String a) {
        a = "2222";
    }

    @Test
    public void test1() throws InterruptedException {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(4,4,0, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        Thread.sleep(2000);
        while (true){
            executor.execute(new MyTask(executor));
            System.out.println(executor.getQueue().size());
        }
        //executor.shutdown();
        //while(executor.awaitTermination(1, TimeUnit.SECONDS));
    }

    class MyTask implements Runnable{
        ExecutorService executor;
        ByteBuffer buffer;

        public MyTask(ExecutorService executor) {
            this.executor = executor;
            this.buffer = ByteBuffer.allocate(1048576);
        }

        @SneakyThrows
        @Override
        public void run() {
            Future f1 = executor.submit(()-> System.out.println(222));
            Thread.sleep(100);
            Future f2 = executor.submit(()-> System.out.println(333));
            f1.get();
            f2.get();
        }
    }
}