package com.example.demo.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author yangjinyu
 * @time 2021/6/11 11:45
 */
public class ModuleDemo {
    public static void main(String[] args) {
        int size = 10001;
        int x = 1000000;
        int y = 2000000;
        List<Integer> unitList = new ArrayList<>();
        // 取10001个1000000-2000000之间的数
        while (size-- > 0) {
            unitList.add(new Random().nextInt(y + 1 - x) + x);
        }
        int exceptTotal = new BigDecimal(unitList.size()).divide(new BigDecimal(10), RoundingMode.CEILING).intValue();
        int realityTotal = 0;
        Map<Integer, List<Integer>> integers = unitList.stream().collect(Collectors.groupingBy(id -> Math.floorMod(id, 5)));
        for (List<Integer> list : integers.values()) {
            realityTotal += new BigDecimal(list.size()).divide(new BigDecimal(10), RoundingMode.CEILING).intValue();
        }
        System.out.println(exceptTotal+"--"+realityTotal);
    }
}