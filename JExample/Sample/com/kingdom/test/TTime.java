package com.kingdom.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TTime {
    public static void main(String[] args) {
        Date date = new Date();
        String fmt = "yy-mm-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        System.out.println(sdf.format(date));

        getCurTime();
        getCalendar();
        TimeStamp();
    }

    /**
     * 格式化时间
     */
    public static void TimeFormat() {
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        System.out.println(sdf.format(date));
    }

    /**
     * 获取当前时间
     */
    public static void getCurTime() {
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yy-MM-dd HH:mm:ss a");
        Date date = new Date();// 获取当前时间
        System.out.println("现在时间：" + sdf.format(date)); // 输出已经格式化的现在时间（24小时制）
    }

    /**
     *  获取年份、月份等
     */
    public static void getCalendar() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int dow = cal.get(Calendar.DAY_OF_WEEK);
        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int doy = cal.get(Calendar.DAY_OF_YEAR);

        System.out.println("当期时间: " + cal.getTime());
        System.out.println("日期: " + day);
        System.out.println("月份: " + month);
        System.out.println("年份: " + year);
        System.out.println("一周的第几天: " + dow);  // 星期日为一周的第一天输出为 1，星期一输出为 2，以此类推
        System.out.println("一月中的第几天: " + dom);
        System.out.println("一年的第几天: " + doy);
    }

    /**
     * 时间戳转换成时间
     */
    public static void TimeStamp(){
        Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(Long.parseLong(String.valueOf(timeStamp)));
        String sd = sdf.format(date);   // 时间戳转换成时间
        System.out.println("时间戳：" + timeStamp + " 转成时间： " + sd);
    }
}