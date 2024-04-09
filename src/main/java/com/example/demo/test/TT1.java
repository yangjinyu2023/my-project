package com.example.demo.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author yangjinyu
 * @time 2023/11/14 18:48
 */
public class TT1 {
    public Pair<Boolean, Integer> t90(){
        return new ImmutablePair<>(Boolean.FALSE, 2);
    }

    public static void main(String[] args) {
        new TT1().tt();
    }
    public void tt(){
        Map<String, String> attr = new HashMap<>();
        attr.put("1", "1");
        attr.remove("1");
        attr.remove("2");
        System.out.println(attr);
        System.out.println(JSON.toJSONString(null));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
        Date date = new Date(new Date().getTime() + TimeUnit.HOURS.toMillis(48));
        System.out.println(sdf.format(date));
        // 创建 Calendar 对象并设置为当前日期和时间
        Calendar calendar = Calendar.getInstance();

        // 将时间调整为三天前的零点
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        System.out.println(sdf.format(calendar.getTime()));

        // 将时间调整为今天的二十四点
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(sdf.format(calendar.getTime()));


        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        System.out.println(list.toString());
        System.out.println(list.toArray());
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(t90().getLeft());
        System.out.println(t90().getRight());
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(4, Arrays.asList(4, 11));
        map.put(96, Arrays.asList(9605, 9607));
        Integer siteType = 4, subType = 9607;
        boolean allow = Optional.ofNullable(map.get(siteType)).map(l -> l.contains(subType)).orElse(false);
        System.out.println(allow);

        JSONObject jsonObject = new JSONObject();
        String a = "1";
        String b = "2";
        jsonObject.put("1","1");
        TT3 tt3 = new TT3();
        Predicate<Void> predicate = t->{
            return a.equals(b) && jsonObject.get("1").equals("1") && tt3.do2();
        };
        TT2 tt2=new TT2();
        tt2.setPredicate(predicate);
        tt3.do1(tt2);
    }
}