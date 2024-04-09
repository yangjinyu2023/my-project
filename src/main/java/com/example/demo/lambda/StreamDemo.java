package com.example.demo.lambda;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("1", "11");
        map1.put("2", "22");
        map1.put("3", "7");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("1", "11");
        map2.put("2", "22");
        map2.put("3", "4");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("1", "11");
        map3.put("2", "22");
        map3.put("3", "5");
        Map<String, Object> map4 = new HashMap<>();
        map4.put("1", "11");
        map4.put("2", "22");
        map4.put("3", "1");
        Map<String, Object> map5 = new HashMap<>();
        map5.put("1", "11");
        map5.put("2", "22");
        map5.put("3", "2");
        Map<String, Object> map6 = new HashMap<>();
        map6.put("1", "11");
        map6.put("2", "22");
        map6.put("3", "2");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);

        BigDecimal result = list.stream().filter(m ->
                "00".equals(m.get("1").toString()))
                .map(m -> new BigDecimal(m.get("1").toString()))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        // 输出0，filter过滤后没有符合条件的项，orElse得到0，使用get会报错java.util.NoSuchElementException: No value present
        System.out.println(result);

        list = list.stream().sorted(Comparator.comparingInt(m ->
                Integer.parseInt(m.get("3").toString()))).collect(Collectors.toList());
        List<Map<String, Object>> subList = new ArrayList<>();
        subList.add(list.get(0));
        for (int index = 1; index < list.size(); index++) {
            int lastYearMonth = Integer.parseInt(list.get(index - 1).get("3").toString());
            Map<String, Object> currentMap = list.get(index);
            if (Integer.parseInt(currentMap.get("3").toString()) - lastYearMonth > 1) {
                System.out.println(subList.toString());
                subList.clear();
            }
            subList.add(currentMap);
        }
        if(!subList.isEmpty()){
            System.out.println(subList.toString());
        }
    }
}