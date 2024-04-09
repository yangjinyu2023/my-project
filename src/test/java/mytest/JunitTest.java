package mytest;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.Test;

import com.xxl.job.core.biz.client.AdminBizClient;

public class JunitTest {
    @Test
    public void test(){
        // 为了测试maven的test scope
        AdminBizClient adminBizClient;
    }
    @Test
    public void t1(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(3));
        executor.prestartCoreThread();
        for (int i = 0; i < 100; i++) {
            executor.execute(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
    @Test
    public void t2(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("AAB001","1");
        map1.put("AAE003","202205");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("AAB001","1");
        map2.put("AAE003","202204");
        list.add(map1);
        list.add(map2);
        list= list.stream().sorted(Comparator.comparing(m->Integer.valueOf(m.get("AAE003").toString()))).collect(Collectors.toList());
        Map < String, List < Map < String, Object > > > groupMap = list.stream()
                .collect(Collectors.groupingBy(m -> m.get("AAB001").toString() + m.get("AAE003").toString()));
        for (List < Map < String, Object > > subList : groupMap.values()) {
            System.out.println(subList.get(0));
        }
    }

    @Test
    public void t3(){
        List<Map<String, Object>> list = new ArrayList(){
            {
                add(new HashMap(){{put("111", 111);put("222",222);}});
                add(new HashMap(){{put("111", 111);put("222",222);}});
                add(new HashMap(){{put("111", 222);put("222",222);}});
            }
        };
        List<Map<String, Object>> list1 = list.stream().filter(m->m.get("111").toString().equals("111")).collect(Collectors.toList());
        list1.forEach(m->m.put("222",333));
        System.out.println(list);
    }
}