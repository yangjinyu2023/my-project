package com.example.demo.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 日期相关操作工具类
 *
 * @author yangjinyu
 */
public class DateUtil {

    private static Log log = LogFactory.getLog(DateUtil.class);

    /**
     * 获取当前系统年月
     *
     * @return 系统年月
     */
    public static String getCurrentYearMonth() {
        return DateUtil.getSysdate(6);
    }

    /**
     * 加月数
     *
     * @param originalYearMonth 原年月yyyyMM
     * @param months 间隔月数
     * @return yyyyMM
     */
    public static String addMonths(String originalYearMonth, int months) {
        if (originalYearMonth == null) {
            throw new RuntimeException("原年月不能为空，请检查！");
        }

        String resultYearMonth = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        try {
            Date date = simpleDateFormat.parse(originalYearMonth);
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            calender.add(Calendar.MONTH, months);
            resultYearMonth = simpleDateFormat.format(calender.getTime());

        }
        catch (ParseException e) {
            log.error(e);
        }

        return resultYearMonth;
    }

    public static Integer addMonths(Integer originalYearMonth, int months) {
        return Integer.parseInt(addMonths(String.valueOf(originalYearMonth), months));
    }

    public static Long addMonths(Long originalYearMonth, int months) {
        return Long.parseLong(addMonths(String.valueOf(originalYearMonth), months));
    }

    /**
     * 获取某个月最后一天
     *
     * @param yearMonth 年月
     * @return yyyyMMdd
     */
    public static String getLastDayOfMonth(String yearMonth) {
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(yearMonth) / 100;
        int month = Integer.parseInt(yearMonth) % 100;
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(cal.getTime());
    }

    public static Integer getLastDayOfMonth(Integer yearMonth) {
        return Integer.parseInt(getLastDayOfMonth(String.valueOf(yearMonth)));
    }

    public static Long getLastDayOfMonth(Long yearMonth) {
        return Long.parseLong(getLastDayOfMonth(String.valueOf(yearMonth)));
    }

    /**
     * 两个时间的间隔(YMD)
     *
     * @param startDay 开始时间
     * @param endDay 终止时间
     */
    public static long daysBetween(String startDay, String endDay) {
        if (startDay == null || endDay == null) {
            throw new RuntimeException("daysBetween的开始时间和终止时间不能为空，请检查！");
        }

        long days = 0;
        try {
            SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
            Date a = dft.parse(startDay);
            Date b = dft.parse(endDay);
            days = (b.getTime() - a.getTime()) / (24 * 60 * 60 * 1000) + 1;

        }
        catch (ParseException pe) {
            log.error(pe);
        }

        return days;
    }

    public static String addDays(String originalDate, int days) {
        if (originalDate == null) {
            throw new RuntimeException("原日期不能为空，请检查！");
        }

        String resultDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = simpleDateFormat.parse(originalDate);
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            calender.add(Calendar.DATE, days);
            resultDate = simpleDateFormat.format(calender.getTime());

        }
        catch (ParseException e) {
            log.error(e);
        }

        return resultDate;
    }

    public static long daysBetween(Integer startDay, Integer endDay) {
        return daysBetween(String.valueOf(startDay), String.valueOf(endDay));
    }

    public static long daysBetween(Long startDay, Long endDay) {
        return daysBetween(String.valueOf(startDay), String.valueOf(endDay));
    }

    /**
     * 两个年月之前的间隔
     *
     * @param startYearMonth 开始年月
     * @param endYearMonth 终止年月
     */
    public static int monthsBetween(String startYearMonth, String endYearMonth) {
        if (startYearMonth == null || endYearMonth == null) {
            throw new RuntimeException("开始年月和终止年月不能为空，请检查！");
        }

        int months;
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
            Date startDate1 = fmt.parse(startYearMonth);

            Calendar starCal = Calendar.getInstance();
            starCal.setTime(startDate1);

            int sYear = starCal.get(Calendar.YEAR);
            int sMonth = starCal.get(Calendar.MONTH);

            Date endDate1 = fmt.parse(endYearMonth);
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(endDate1);

            int eYear = endCal.get(Calendar.YEAR);
            int eMonth = endCal.get(Calendar.MONTH);

            months = ((eYear - sYear) * 12 + (eMonth - sMonth) + 1);

        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return months;
    }

    public static int monthsBetween(Integer startYearMonth, Integer endYearMonth) {
        return monthsBetween(String.valueOf(startYearMonth), String.valueOf(endYearMonth));
    }

    public static int monthsBetween(Long startYearMonth, Long endYearMonth) {
        return monthsBetween(String.valueOf(startYearMonth), String.valueOf(endYearMonth));
    }

    /**
     * 计算年龄
     *
     * @param birthday 生日
     * @param yearMonth 截止年月
     */
    public static int calculateAge(String birthday, String yearMonth) {
        if (birthday == null) {
            throw new RuntimeException("未传入生日参数！");
        }
        if (yearMonth == null) {
            throw new RuntimeException("未传入截止年月参数！");
        }

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(birthday);
            simpleDateFormat = new SimpleDateFormat("yyyyMM");
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(yearMonth);
        }
        catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
        return (DateUtil.monthsBetween(birthday.substring(0, 6), yearMonth) - 2) / 12;
    }

    public static int calculateAge(Integer birthday, Integer yearMonth) {
        return calculateAge(String.valueOf(birthday), String.valueOf(yearMonth));
    }

    public static int calculateAge(Long birthday, Long yearMonth) {
        return calculateAge(String.valueOf(birthday), String.valueOf(yearMonth));
    }

    /**
     * 根据开始终止年月获取年月集合
     * 
     * @param startYearMonth 开始年月
     * @param endYearMonth 终止年月
     * @return 年月集合
     */
    public static List < String > getDistanceYearMonths(String startYearMonth, String endYearMonth) {
        List < String > yearMonths = new ArrayList <>();
        for (long yearMonth = Long.parseLong(startYearMonth); yearMonth <= Long
                .parseLong(endYearMonth); yearMonth = addMonths(yearMonth, 1)) {
            yearMonths.add(String.valueOf(yearMonth));
        }
        return yearMonths;
    }

    public static List < Integer > getDistanceYearMonths(Integer startYearMonth, Integer endYearMonth) {
        return getDistanceYearMonths(String.valueOf(startYearMonth), String.valueOf(endYearMonth)).stream()
                .map(Integer::valueOf).collect(Collectors.toList());
    }

    public static List < Long > getDistanceYearMonths(Long startYearMonth, Long endYearMonth) {
        return getDistanceYearMonths(String.valueOf(startYearMonth), String.valueOf(endYearMonth)).stream()
                .map(Long::valueOf).collect(Collectors.toList());
    }

    /**
     * 获取系统时间
     *
     * @param formatLength 获取系统当前时间
     */
    public static String getSysdate(int formatLength) {
        String dateFormat;
        switch (formatLength) {
        case 4:
            dateFormat = "yyyy";
            break;
        case 7:
            dateFormat = "yyyy-MM";
            break;
        case 6:
            dateFormat = "yyyyMM";
            break;
        case 8:
            dateFormat = "yyyyMMdd";
            break;
        case 10:
            dateFormat = "yyyy-MM-dd";
            break;
        default:
            dateFormat = "yyyyMMddHHmmss";
        }
        SimpleDateFormat dft = new SimpleDateFormat(dateFormat);

        return dft.format(new Date());
    }

    /**
     * 获取日志时间
     *
     * @return 日志时间
     */
    public static Long getLogTime() {
        return Long.parseLong(getSysdate(14));
    }

    /**
     * 获取距离当前时间指定天数的时间
     * 
     * @param distance 指定天数
     * @return 指定日期
     */
    public static Integer getPointedDateDistanceFromCurrent(int distance) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, distance);
        return Integer.valueOf(format.format(calendar.getTime()));
    }

    /**
     * 获取昨天日期
     * 
     * @return 昨天日期
     */
    public static Integer getYesterday() {
        return getPointedDateDistanceFromCurrent(-1);
    }
}
