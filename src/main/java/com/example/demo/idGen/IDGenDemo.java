package com.example.demo.idGen;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class IDGenDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<DeclareItemCondition> declareItemConditions = new ArrayList<>();
        DeclareItemCondition d1 = new DeclareItemCondition();
        d1.setIssue(99L);
        d1.setCollectSeq("1");
        d1.setInsuTypes("110,120,130,160");
        d1.setStartBusiYearMonth(201909L);
        d1.setEndBusiYearMonth(202009L);
        DeclareItemCondition d2 = new DeclareItemCondition();
        d1.setIssue(99L);
        d2.setAuditSeq("2");
        d2.setInsuTypes("110,130,140");
        d2.setStartBusiYearMonth(201801L);
        d2.setEndBusiYearMonth(201903L);
        declareItemConditions.add(d1);
        declareItemConditions.add(d2);

        declareItemConditions.stream().filter(a->1==2).map(DeclareItemCondition::getIssue).collect(Collectors.toList());

        System.out.println(SICP4FeeTypeEnum.正常核定);
        System.out.println(declareItemConditions.stream().filter(d->d.getIssue() == 0L).mapToLong(DeclareItemCondition::getIssue).sum());
        List<String> list2 = declareItemConditions.stream().filter(c -> StringUtils.isNotBlank(c.getInsuTypes()))
                .flatMap(m -> Arrays.stream(m.getInsuTypes().split(","))).distinct().collect(Collectors.toList());
        System.out.println(list2);

        List<Long> busiYearMonths = declareItemConditions.stream()
                .filter(e -> Objects.nonNull(e.getStartBusiYearMonth()) && Objects.nonNull(e.getEndBusiYearMonth()))
                .flatMap(e -> getDistanceYearMonths(e.getStartBusiYearMonth(), e.getEndBusiYearMonth())
                        .stream())
                .collect(Collectors.toList());
        System.out.println(busiYearMonths);

        //        IDGenImpl idGen = new IDGenImpl();
//        idGen.init();
//
//        long start = System.currentTimeMillis();
//        ExecutorService executor = Executors.newFixedThreadPool(10);
//        List<Future> futureList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            futureList.add(executor.submit(() -> {
//                for (int j = 0; j < 10000; j++) {
//                    System.out.println(idGen.get());
//                }
//            }));
//        }
//        for (Future future : futureList) {
//            future.get();
//        }
//        System.out.println(System.currentTimeMillis() - start);
//        executor.shutdown();
    }

    /**
     * 根据开始终止年月获取年月集合
     *
     * @param startYearMonth 开始年月
     * @param endYearMonth   终止年月
     * @return 年月集合
     */
    public static List<Long> getDistanceYearMonths(Long startYearMonth, Long endYearMonth) {
        List<Long> yearMonths = new ArrayList<>();
        for (long yearMonth = startYearMonth; yearMonth <= endYearMonth; yearMonth = Long
                .parseLong(addMonths(String.valueOf(yearMonth), 1))) {
            yearMonths.add(yearMonth);
        }
        return yearMonths;
    }

    public static String addMonths(String originalYearMonth, int months) {
        String resultYearMonth = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        try {
            Date date = simpleDateFormat.parse(originalYearMonth);
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            calender.add(Calendar.MONTH, months);
            resultYearMonth = simpleDateFormat.format(calender.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return resultYearMonth;
    }
}